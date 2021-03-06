package com.codespace.exchange.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.codespace.exchange.provider.ExchangeRateProvider;
import com.codespace.exchange.service.ExchangeRateService;

/**
 * @author ugureli
 *
 */
@Component
public class ScheduledTaskExecutor {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskExecutor.class);

	private static final int INTERVAL = 900000;

	@Autowired
	ExchangeRateProvider exchangeRateProvider;
	@Autowired
	ExchangeRateService exchangeRateService;

	@Scheduled(fixedRate = INTERVAL)
	public void scheduleTaskWithFixedRate() {
		logger.info("Task loading rates from fixer.io...");
		exchangeRateService.saveAll(exchangeRateProvider.getRates());
		logger.info("Task succcesfully saved rates from fixer.io");
	}
}