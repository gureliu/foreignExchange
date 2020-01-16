package com.codespace.exchange.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.codespace.exchange.dto.FixerExchangeRateDTO;
import com.codespace.exchange.entity.Rate;

/**
 * @author ugureli
 *
 */
@Component
public class ExchangeRateProvider {

	private static final Logger logger = LoggerFactory.getLogger(ExchangeRateProvider.class);

	@Autowired
	RestTemplate restTemplate;

	/**
	 * @return
	 */
	public List<Rate> getRates() {
		final String uri = "http://data.fixer.io/api/latest?access_key=9a70d3828d7b94f65de723ebeffc4ec5";

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<FixerExchangeRateDTO> response = restTemplate.exchange(uri, HttpMethod.GET, entity, FixerExchangeRateDTO.class);
		logger.info("RateProvider status:" + response.getStatusCode());
		logger.info("RateProvider data:" + response.getBody());
		return convertDTOToDAO(response);
	}

	/**
	 * @param response
	 * @return
	 */
	private List<Rate> convertDTOToDAO(ResponseEntity<FixerExchangeRateDTO> response) {
		FixerExchangeRateDTO dto = response.getBody();
		String base = dto.getBase();
		Map<String, BigDecimal> symbolRates = dto.getRates();
		List<Rate> list = new ArrayList<Rate>();
		symbolRates.forEach((k, v) -> list.add(new Rate(base, k, v)));
		return list;
	}

}
