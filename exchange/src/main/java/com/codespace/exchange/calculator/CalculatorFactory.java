package com.codespace.exchange.calculator;

/**
 * Enum factory
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
			return new RateCalculator();
		default:
			break;
		}
		return null;
	}

}