package com.codespace.exchange.service;

import java.util.List;

import com.codespace.exchange.dto.RateDTO;
import com.codespace.exchange.entity.Rate;
import com.codespace.exchange.exception.ResourceNotFoundException;

/**
 * @author ugureli
 *
 */
public interface ExchangeRateService {

	public void saveAll(List<Rate> rates);

	public RateDTO getRate(String base, String symbol) throws ResourceNotFoundException;

	public void saveAllForNonRelational(List<Rate> rates);
}
