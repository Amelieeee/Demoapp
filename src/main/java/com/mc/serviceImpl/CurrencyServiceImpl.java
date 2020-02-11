package com.mc.serviceImpl;

import com.mc.model.Currency;
import com.mc.repo.CurrencyRepository;
import com.mc.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	CurrencyRepository currencyRepo;


	@Override
	public List<Currency> getAllCurrencies() {
		System.out.println("Get all Currencies...");
		List<Currency> currencies = new ArrayList<>();

		try {
			currencyRepo.findAll().forEach(currencies::add);

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl getAllCurrencies throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			return currencies;
		}
	}

	@Override
	public Currency createCurrency(Currency currency) {
		System.out.println("Create Currency with ID = " + currency.getId() + ", name = " + currency.getName());
		Currency _currency = null;

		try {
			_currency = currencyRepo.save(
					new Currency(currency.getId(), currency.getName()));

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl createCurrency throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			return _currency;
		}
	}

	@Override
	public void deleteCurrencyById(String id) {
		System.out.println("Delete Currency with ID = " + id + "...");

		try {
			currencyRepo.deleteById(id);

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl deleteCurrencyById throws exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteAllCurrencies() {
		System.out.println("Delete All Currencies...");

		try {
			currencyRepo.deleteAll();

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl deleteAllCurrencies throws exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public List<Currency> findByName(String name) {

		List<Currency> Currencies = new ArrayList<>();

		try {
			Currencies = currencyRepo.findByName(name);

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl findByName throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			return Currencies;
		}

	}

	@Override
	public Currency updateCurrency(String id, Currency currency) {
		System.out.println("Update Currency with ID = " + id + "...");

		Currency updatedCurrency = null;

		try {
			Optional<Currency> customerData = currencyRepo.findById(id);

			if (customerData.isPresent()) {
				Currency _currency = customerData.get();
				_currency.setId(currency.getId());
				_currency.setName(currency.getName());
				updatedCurrency = currencyRepo.save(_currency);

			} else {
				updatedCurrency = null;
			}

		} catch(Exception ex){
			System.out.println("CurrencyServiceImpl updateCurrency throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			return updatedCurrency;
		}
	}

}
