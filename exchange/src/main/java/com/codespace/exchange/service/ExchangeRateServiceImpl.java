package com.codespace.exchange.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codespace.exchange.dto.RatePairDTO;
import com.codespace.exchange.entity.RatePair;
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
	public RatePairDTO getRate(String base, String symbol) throws ResourceNotFoundException {
		Optional<RatePair> data = repository.findByBaseAndSymbol(base, symbol);
		if (data.isPresent()) {
			return modelMapper.map(data.get(), RatePairDTO.class);
		} else {
			throw new ResourceNotFoundException("No rate data found for base or symbol");
		}
	}

	/**
	 *
	 */
	@Override
	public boolean saveAll(List<RatePair> rates) {
		for (RatePair rate : rates) {
			Optional<RatePair> data = repository.findByBaseAndSymbol(rate.getBase(), rate.getSymbol());
			if (data.isPresent()) {
				repository.save(data.get());
			} else {
				repository.save(rate);
			}
		}
		return true;
	}

	/**
	 * POC. not used. can be used for non relational db design like nosql dbs
	 */
	@Override
	public void saveAllForNonRelational(List<RatePair> rates) {
		repository.deleteAll();
		repository.saveAll(rates);
	}

}
