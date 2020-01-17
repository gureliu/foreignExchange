package com.codespace.exchange.service;

import java.util.List;

import com.codespace.exchange.dto.RatePairDTO;
import com.codespace.exchange.entity.RatePair;
import com.codespace.exchange.exception.ResourceNotFoundException;

/**
 * @author ugureli
 *
 */
public interface ExchangeRateService {

	public boolean saveAll(List<RatePair> rates);

	public RatePairDTO getRate(String base, String symbol) throws ResourceNotFoundException;

	public void saveAllForNonRelational(List<RatePair> rates);
}
