package com.codespace.exchange.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespace.exchange.dto.RateDTO;
import com.codespace.exchange.entity.Rate;
import com.codespace.exchange.exception.ResourceNotFoundException;
import com.codespace.exchange.repository.ExchangeRateRepository;

/**
 * @author ugureli
 *
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired
	ExchangeRateRepository repository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 *
	 */
	@Override
	public RateDTO getRate(String base, String symbol) throws ResourceNotFoundException {
		Optional<Rate> data = repository.findByBaseAndSymbol(base, symbol);
		if (data.isPresent()) {
			return modelMapper.map(data.get(), RateDTO.class);
		} else {
			throw new ResourceNotFoundException("No rate data found for base or symbol");
		}
	}

	/**
	 *
	 */
	@Override
	public void saveAll(List<Rate> rates) {
		for (Rate rate : rates) {
			Optional<Rate> data = repository.findByBaseAndSymbol(rate.getBase(), rate.getSymbol());
			if (data.isPresent()) {
				repository.save(data.get());
			} else {
				repository.save(rate);
			}
		}
	}

	/**
	 * POC. not used. can be used for non relational db design like nosql dbs
	 */
	@Override
	public void saveAllForNonRelational(List<Rate> rates) {
		repository.deleteAll();
		repository.saveAll(rates);
	}

}
