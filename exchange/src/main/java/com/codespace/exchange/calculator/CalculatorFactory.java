package com.codespace.exchange.calculator;

/**
 * 
 * @author ugureli
 *
 */
public enum CalculatorFactory {

	INSTANCE;

	private CalculatorFactory() {
	}

	/**
	 * @param name
	 * @return
	 */
	public Calculator getCalculator(CalculatorType name) {
		switch (name) {
		case RATE:
			return new RateConverter();
		default:
			break;
		}
		return null;
	}

}