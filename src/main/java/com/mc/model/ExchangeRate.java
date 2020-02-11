package com.mc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "ExchangeRate")
public class ExchangeRate {
	@Id
	private String id;

	private String originalCurrencyName;
	private String targetCurrencyName;
	private BigDecimal exchangeRate;


	public ExchangeRate(String id, String originalCurrencyName, String targetCurrencyName, BigDecimal exchangeRate) {
		this.id = id;
		this.originalCurrencyName = originalCurrencyName;
		this.targetCurrencyName = targetCurrencyName;
		this.exchangeRate = exchangeRate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalCurrencyName() {
		return originalCurrencyName;
	}

	public void setOriginalCurrencyName(String originalCurrencyName) {
		this.originalCurrencyName = originalCurrencyName;
	}

	public String getTargetCurrencyName() {
		return targetCurrencyName;
	}

	public void setTargetCurrencyName(String targetCurrencyName) {
		this.targetCurrencyName = targetCurrencyName;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", originalCurrencyName=" + originalCurrencyName + ", targetCurrencyName=" + targetCurrencyName + ", exchangeRate=" + exchangeRate + "]";
	}
}
