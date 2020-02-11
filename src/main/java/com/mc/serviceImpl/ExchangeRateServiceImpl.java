package com.mc.serviceImpl;

import com.mc.Util.CurrencyUtil;
import com.mc.model.ExchangeRate;
import com.mc.repo.ExchangeRateRepository;
import com.mc.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired
	ExchangeRateRepository exchangeRateRepo;


	@Override
	public List<ExchangeRate> getAllExchangeRates() {
		System.out.println("Get all ExchangeRate...");
		List<ExchangeRate> exchangeRates = new ArrayList<>();

		try {
			exchangeRateRepo.findAll().forEach(exchangeRates::add);

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl getAllExchangeRates throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			return exchangeRates;
		}
	}

	@Override
	public void deleteAllExchangeRates() {
		System.out.println("Delete All ExchangeRates...");

		try {
			exchangeRateRepo.deleteAll();

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl deleteAllExchangeRates throws exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public ExchangeRate createExchangeRate(ExchangeRate exchangeRate){
		System.out.println("Create ExchangeRate with ID = " + exchangeRate.getId()
				+ ",  originalCurrencyName= " + exchangeRate.getOriginalCurrencyName() + ", targetCurrencyName= "
				+ exchangeRate.getTargetCurrencyName() + ", exchangeRate= " + exchangeRate.getExchangeRate());

		ExchangeRate _exchangeRate = null;

		try {
			_exchangeRate = exchangeRateRepo.save(
					new ExchangeRate(exchangeRate.getId(), exchangeRate.getOriginalCurrencyName(),
					exchangeRate.getTargetCurrencyName(), exchangeRate.getExchangeRate()));

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl createCurrency throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			return _exchangeRate;
		}
	}

	@Override
	public ExchangeRate getExchangeRate(String originalCurrencyName, String targetCurrencyName) {
		ExchangeRate exchangeRate = null;

		try{
			List<ExchangeRate> exchangeRates = exchangeRateRepo.findByOriginalCurrencyName(originalCurrencyName);

			if(exchangeRates != null && !exchangeRates.isEmpty() && exchangeRates.size() > 0) {
				for(int i=0; i<exchangeRates.size(); i++) {
					if(exchangeRates.get(i).getTargetCurrencyName().equals(targetCurrencyName)) {
						exchangeRate = exchangeRates.get(0);
						break;
					}
				}
			}

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl getExchangeRate throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			return exchangeRate;
		}

	}

	@Override
	public BigDecimal getExchangedAmount(String originalCurrencyName, String targetCurrencyName, BigDecimal exchangeAmount) {
		BigDecimal exchangedAmount = null;

		try{
			ExchangeRate exchangeRate = this.getExchangeRate(originalCurrencyName, targetCurrencyName);

			if(exchangeRate != null && exchangeRate.getExchangeRate() != null) {
				exchangedAmount = exchangeRate.getExchangeRate().multiply(exchangeAmount);
				exchangedAmount = CurrencyUtil.round(exchangedAmount, 2, false);
			}

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl getExchangeAmount throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			return exchangedAmount;
		}

	}
}
