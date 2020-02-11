package com.mc.service;

import com.mc.model.Currency;
import com.mc.model.ExchangeRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface ExchangeRateService {
	public abstract List<ExchangeRate> getAllExchangeRates();
	public abstract void deleteAllExchangeRates() ;
	public abstract ExchangeRate createExchangeRate(ExchangeRate exchangeRate);
	public abstract ExchangeRate getExchangeRate(String originalCurrencyName, String targetCurrencyName);
	public abstract BigDecimal getExchangedAmount(String originalCurrencyName, String targetCurrencyName, BigDecimal exchangeAmount);
}
