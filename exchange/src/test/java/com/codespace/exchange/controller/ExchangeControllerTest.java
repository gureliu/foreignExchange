package com.codespace.exchange.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.codespace.exchange.dto.ConversionParameterDTO;
import com.codespace.exchange.provider.ExchangeRateProvider;
import com.codespace.exchange.service.ExchangeRateService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * the exception handling tests etc. many of them can be done but in this PoC
 * 
 * @author ugureli
 *
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExchangeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ExchangeRateProvider provider;
	@Autowired
	private ExchangeRateService service;

	@Autowired
	private ObjectMapper objectMapper;

	private Date sampleTDate = new Date();

	@Test
	@Order(1)
	public void testSaveAllRates() throws Exception {
		assertTrue(service.saveAll(provider.getRates()));
	}

	@Test
	@Order(2)
	public void testRates() throws Exception {
		this.mockMvc.perform(get("/api/v1/rates/EUR/TRY")).andExpect(status().isOk());
	}

	@Test
	@Order(3)
	public void testConvert() throws Exception {
		ConversionParameterDTO dto = new ConversionParameterDTO("EUR", "TRY", BigDecimal.valueOf(80));
		String writeValueAsString = objectMapper.writeValueAsString(dto);
		this.mockMvc.perform(post("/api/v1/convert").contentType("application/json").content(writeValueAsString)).andExpect(status().isOk());
	}

	/**
	 * PoC same date will be used.. or it is volatile param so it cant be used
	 * 
	 * @throws Exception
	 */
	@Test
	@Order(4)
	public void testGetConversions_withTransactionDate() throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		this.mockMvc.perform(get("/api/v1/conversions/0/20").param("transactionDate", df.format(sampleTDate))).andExpect(status().isOk());
	}
}