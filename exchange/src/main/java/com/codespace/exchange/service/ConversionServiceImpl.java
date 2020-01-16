package com.codespace.exchange.service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codespace.exchange.calculator.Calculator;
import com.codespace.exchange.calculator.CalculatorFactory;
import com.codespace.exchange.calculator.CalculatorType;
import com.codespace.exchange.dto.ConversionDetailDTO;
import com.codespace.exchange.dto.ConversionParameterDTO;
import com.codespace.exchange.dto.ConversionResultDTO;
import com.codespace.exchange.dto.RateDTO;
import com.codespace.exchange.entity.Conversion;
import com.codespace.exchange.entity.Rate;
import com.codespace.exchange.exception.ResourceNotFoundException;
import com.codespace.exchange.repository.ConversionRepository;
import com.codespace.exchange.repository.ExchangeRateRepository;

/**
 * @author ugureli
 *
 */
@Service
public class ConversionServiceImpl implements ConversionService {

	@Autowired
	ConversionRepository conversionRepository;

	@Autowired
	ExchangeRateRepository exchangeRateRepository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 *
	 */
	@Override
	public ConversionResultDTO convert(ConversionParameterDTO conversionDTO) throws ResourceNotFoundException {
		Optional<Rate> data = exchangeRateRepository.findByBaseAndSymbol(conversionDTO.getBase(), conversionDTO.getSymbol());
		if (data.isPresent()) {
			RateDTO rateDTO = modelMapper.map(data.get(), RateDTO.class);
			BigDecimal rate = rateDTO.getRate();
			Calculator calculator = CalculatorFactory.INSTANCE.getCalculator(CalculatorType.RATE);
			BigDecimal targetAmount = calculator.convert(conversionDTO.getAmount(), rate);
			Conversion conversion = new Conversion();
			conversion.setRate(data.get());
			conversion.setSourceAmount(conversionDTO.getAmount());
			conversion.setTargetAmount(targetAmount);
			conversion.setTransactionDate(Calendar.getInstance().getTime());
			conversionRepository.save(conversion);
			return modelMapper.map(conversion, ConversionResultDTO.class);
		} else {
			throw new ResourceNotFoundException("Base or symbol is not found");
		}
	}

	/**
	 *
	 */
	@Override
	public List<ConversionDetailDTO> find(Long transactionId, Date transactionDate, int page, int size) throws ResourceNotFoundException {
		Pageable pageable = createPageRequest(page, size);
		List<Conversion> data = conversionRepository.findByTransactionIdOrTransactionDate(transactionId, transactionDate, pageable);
		if (data.size() > 0) {
			Type listType = new TypeToken<List<ConversionDetailDTO>>() {
			}.getType();
			return modelMapper.map(data, listType);
		} else {
			throw new ResourceNotFoundException("No Record");
		}
	}

	private PageRequest createPageRequest(int page, int size) {
		return PageRequest.of(page, size, Sort.Direction.ASC, "transactionId");
	}

}
