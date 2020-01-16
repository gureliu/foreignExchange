package com.codespace.exchange.calculator;

import java.math.BigDecimal;

/**
 * @author ugureli
 *
 */
public class RateConverter extends Calculator {

	@Override
	public BigDecimal convert(BigDecimal amount, BigDecimal rate) {
		if (amount == null) {
			throw new ArithmeticException("amount can not be null");
		}
		if (rate == null) {
			throw new ArithmeticException("rate can not be null");
		}
		return rate.multiply(amount).round(super.mathContext);
	}

}
