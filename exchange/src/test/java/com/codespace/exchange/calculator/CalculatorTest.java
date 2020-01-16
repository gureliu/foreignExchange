package com.codespace.exchange.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class CalculatorTest {

	@Test
	public void testCalculator() throws Exception {
		Calculator calc = CalculatorFactory.INSTANCE.getCalculator(CalculatorType.RATE);
		BigDecimal rate = BigDecimal.valueOf(10);
		BigDecimal amount = BigDecimal.valueOf(5);
		assertEquals(BigDecimal.valueOf(50), calc.convert(amount, rate));
	}
}