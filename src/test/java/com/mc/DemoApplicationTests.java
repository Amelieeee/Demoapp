package com.mc;

import com.mc.model.Currency;
import com.mc.model.ExchangeRate;
import com.mc.service.CurrencyService;
import com.mc.service.ExchangeRateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private ExchangeRateService exchangeRateService;

	private Currency sgd = new Currency("1", "SGD");
	private Currency usd = new Currency("2", "USD");

	private ExchangeRate sgdToUsd = new ExchangeRate("1", "SGD", "USD", new BigDecimal(0.742164));
	private ExchangeRate usdToSgd = new ExchangeRate("2", "USD", "SGD", new BigDecimal(1.34782));


	@Before
	public void setUp() {
		currencyService.deleteAllCurrencies();
		exchangeRateService.deleteAllExchangeRates();

		Currency sgdSaved = currencyService.createCurrency(sgd);
		assertEquals(sgd.getId(), sgdSaved.getId());
		assertEquals(sgd.getName(), sgdSaved.getName());

		Currency usdSaved = currencyService.createCurrency(usd);
		assertEquals(usd.getId(), usdSaved.getId());
		assertEquals(usd.getName(), usdSaved.getName());

		ExchangeRate exchangeRateSGDtoUSD = exchangeRateService.createExchangeRate(sgdToUsd);
		assertEquals(sgdToUsd.getExchangeRate(), exchangeRateSGDtoUSD.getExchangeRate());

		ExchangeRate exchangeRateUSDtoSGD = exchangeRateService.createExchangeRate(usdToSgd);
		assertEquals(usdToSgd.getExchangeRate(), exchangeRateUSDtoSGD.getExchangeRate());
	}


	@Test
	public void getAllCurrencies() {
		List<Currency> currencies = currencyService.getAllCurrencies();
		assertEquals(2, currencies.size());
	}

	@Test
	public void findByName() {
		List<Currency> currencies = currencyService.findByName("SGD");
		assertEquals(1, currencies.size());
		assertEquals("SGD", currencies.get(0).getName());
	}

	@Test
	public void getAllExchangeRates() {
		List<ExchangeRate> exchangeRates = exchangeRateService.getAllExchangeRates();
		assertEquals(2, exchangeRates.size());
	}
/*
	@Test
	public void getExchangeRate() {
		ExchangeRate exchangeRate = exchangeRateService.getExchangeRate("SGD", "USD");
		assertEquals(new BigDecimal(0.742164), exchangeRate.getExchangeRate());
	}

	@Test
	public void getExchangeAmount() {
		BigDecimal exchangedAmount = exchangeRateService.getExchangedAmount("SGD", "USD", new BigDecimal(19000));
		assertEquals(new BigDecimal(14101.12), exchangedAmount);
	}*/


}
