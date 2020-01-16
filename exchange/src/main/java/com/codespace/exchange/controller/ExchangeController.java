package com.codespace.exchange.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codespace.exchange.dto.ConversionDetailDTO;
import com.codespace.exchange.dto.ConversionParameterDTO;
import com.codespace.exchange.dto.ConversionResultDTO;
import com.codespace.exchange.dto.RateDTO;
import com.codespace.exchange.exception.BadRequestException;
import com.codespace.exchange.exception.ResourceNotFoundException;
import com.codespace.exchange.service.ConversionService;
import com.codespace.exchange.service.ExchangeRateService;

/**
 * @author ugureli
 *
 */
@RestController
@RequestMapping(value = { "/api/v1" }, produces = { "application/vnd.srvc.app-v1.0+json" })
public class ExchangeController {

	@Autowired
	private ConversionService conversionService;
	@Autowired
	private ExchangeRateService exchangeRateService;

	@GetMapping("/rates/{base}/{symbol}")
	public RateDTO getRates(@PathVariable String base, @PathVariable String symbol) throws ResourceNotFoundException {
		return exchangeRateService.getRate(base, symbol);
	}

	@PostMapping("/convert")
	public ConversionResultDTO convert(@Valid @RequestBody ConversionParameterDTO param) throws ResourceNotFoundException {
		return conversionService.convert(param);
	}

	@GetMapping("/listConversions/{page}/{size}")
	public List<ConversionDetailDTO> listConversions(@PathVariable int page, @PathVariable int size, @RequestParam Optional<Long> transactionId,
			@RequestParam @Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<Date> transactionDate) throws ResourceNotFoundException, BadRequestException {

		if (!transactionId.isPresent() && !transactionDate.isPresent()) {
			throw new BadRequestException("Either transactionId or transactionDate shall be provided");
		}
		if (transactionId.isPresent()) {
			return conversionService.find(transactionId.get(), null, page, size);
		}
		return conversionService.find(null, transactionDate.get(), page, size);
	}

}
