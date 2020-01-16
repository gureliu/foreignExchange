package com.codespace.exchange.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ugureli
 *
 */
public class ConversionParameterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank(message = "base shall not be null")
	private String base;
	@NotBlank(message = "amount is mandatory")
	private String symbol;
	@NotNull(message = "amount is mandatory")
	@DecimalMin(value = "0.0", inclusive = false, message = "amount can not be less than 0")
	private BigDecimal amount;

	public ConversionParameterDTO(String base, String symbol, BigDecimal amount) {
		super();
		this.base = base;
		this.symbol = symbol;
		this.amount = amount;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConversionParameterDTO other = (ConversionParameterDTO) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConversionParameterDTO [base=" + base + ", symbol=" + symbol + ", amount=" + amount + "]";
	}

}