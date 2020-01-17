package com.codespace.exchange.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author ugureli
 *
 */
public class ConversionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long transactionId;
	private RatePairDTO ratePair;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date transactionDate;
	private BigDecimal sourceAmount;
	private BigDecimal targetAmount;

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public RatePairDTO getRatePair() {
		return ratePair;
	}

	public void setRatePair(RatePairDTO ratePair) {
		this.ratePair = ratePair;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BigDecimal getSourceAmount() {
		return sourceAmount;
	}

	public void setSourceAmount(BigDecimal sourceAmount) {
		this.sourceAmount = sourceAmount;
	}

	public BigDecimal getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(BigDecimal targetAmount) {
		this.targetAmount = targetAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ratePair == null) ? 0 : ratePair.hashCode());
		result = prime * result + ((sourceAmount == null) ? 0 : sourceAmount.hashCode());
		result = prime * result + ((targetAmount == null) ? 0 : targetAmount.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
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
		ConversionDTO other = (ConversionDTO) obj;
		if (ratePair == null) {
			if (other.ratePair != null)
				return false;
		} else if (!ratePair.equals(other.ratePair))
			return false;
		if (sourceAmount == null) {
			if (other.sourceAmount != null)
				return false;
		} else if (!sourceAmount.equals(other.sourceAmount))
			return false;
		if (targetAmount == null) {
			if (other.targetAmount != null)
				return false;
		} else if (!targetAmount.equals(other.targetAmount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConversionDTO [transactionId=" + transactionId + ", ratePair=" + ratePair + ", transactionDate=" + transactionDate + ", sourceAmount=" + sourceAmount + ", targetAmount=" + targetAmount
				+ "]";
	}

}