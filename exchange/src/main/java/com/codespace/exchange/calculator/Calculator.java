package com.codespace.exchange.calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author ugureli
 *
 */
public abstract class Calculator {

	protected final MathContext mathContext = new MathContext(10, RoundingMode.HALF_DOWN);

	public abstract BigDecimal convert(BigDecimal amount, BigDecimal rate);
}
