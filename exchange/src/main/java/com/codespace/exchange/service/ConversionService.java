package com.codespace.exchange.service;

import java.util.Date;
import java.util.List;

import com.codespace.exchange.dto.ConversionDetailDTO;
import com.codespace.exchange.dto.ConversionParameterDTO;
import com.codespace.exchange.dto.ConversionResultDTO;
import com.codespace.exchange.exception.ResourceNotFoundException;

/**
 * @author ugureli
 *
 */
public interface ConversionService {

	public ConversionResultDTO convert(ConversionParameterDTO parameter) throws ResourceNotFoundException;

	public List<ConversionDetailDTO> find(Long transactionId, Date transactionDate, int first, int last) throws ResourceNotFoundException;

}
