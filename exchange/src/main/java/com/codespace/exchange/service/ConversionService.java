package com.codespace.exchange.service;

import java.util.Date;
import java.util.List;

import com.codespace.exchange.dto.ConversionDTO;
import com.codespace.exchange.dto.ConversionParameterDTO;
import com.codespace.exchange.exception.ResourceNotFoundException;

/**
 * @author ugureli
 *
 */
public interface ConversionService {

	public ConversionDTO convert(ConversionParameterDTO parameter) throws ResourceNotFoundException;

	public List<ConversionDTO> findBy(Long transactionId, Date transactionDate, int first, int last) throws ResourceNotFoundException;

}
