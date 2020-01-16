package com.codespace.exchange.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author ugureli
 *
 */
@Entity
public class Rate {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "Name is mandatory")
	@Size(min = 2)
	private String base;

	@NotBlank(message = "Symbol is mandatory")
	@Size(min = 2)
	private String symbol;

	@NotNull(message = "Rate is mandatory")
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal rate;

	public Rate() {
	}

	public Rate(@NotBlank(message = "Name is mandatory") @Size(min = 2) String base, @NotBlank(message = "Symbol is mandatory") @Size(min = 2) String symbol,
			@NotBlank(message = "Rate is mandatory") @DecimalMin(value = "0.0", inclusive = false) BigDecimal rate) {
		super();
		this.base = base;
		this.symbol = symbol;
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
		Rate other = (Rate) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
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
		return "Rate [id=" + id + ", base=" + base + ", symbol=" + symbol + ", rate=" + rate + "]";
	}

}