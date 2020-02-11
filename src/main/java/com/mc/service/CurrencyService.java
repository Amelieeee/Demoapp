package com.mc.service;

import com.mc.model.Currency;
import com.mc.model.Currency;
import com.mc.model.ExchangeRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface CurrencyService {
	public abstract List<Currency> getAllCurrencies();
	public abstract Currency createCurrency(Currency currency);
	public abstract void deleteCurrencyById(String id);
	public abstract void deleteAllCurrencies();
	public abstract List<Currency> findByName(String name);
	public abstract Currency updateCurrency(String id, Currency currency);
}
