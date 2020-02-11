package com.mc.controller;

import com.mc.model.ExchangeRate;
import com.mc.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ExchangeRateController {

	@Autowired
	ExchangeRateService exchangeRateService;


	@GetMapping("/exchangeRates")
	public List<ExchangeRate> getAllExchangeRates() {
		List<ExchangeRate> exchangeRates = new ArrayList<>();

		try {
			exchangeRates = exchangeRateService.getAllExchangeRates();

		} catch(Exception ex){
			System.out.println("ExchangeRateController getAllExchangeRates throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			return exchangeRates;
		}

	}

	@DeleteMapping("/exchangeRates/delete")
	public ResponseEntity<String> deleteAllExchangeRates() {
		exchangeRateService.deleteAllExchangeRates();
		return new ResponseEntity<>("All ExchangeRates have been deleted!", HttpStatus.OK);
	}

	@PostMapping("/exchangeRates/create")
	public ResponseEntity<ExchangeRate> postExchangeRate(@RequestBody ExchangeRate exchangeRate) {
		ExchangeRate updatedExchangeRate = null;

		try {
			updatedExchangeRate = exchangeRateService.createExchangeRate(exchangeRate);

		} catch(Exception ex){
			System.out.println("ExchangeRateController postExchangeRate throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			if (updatedExchangeRate != null) {
				return new ResponseEntity<>(updatedExchangeRate, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		}
	}

	@GetMapping("currencies/from/{originalCurrencyName}/to/{targetCurrencyName}")
	public ResponseEntity<ExchangeRate> getExchangeRate(@PathVariable String originalCurrencyName,
										@PathVariable String targetCurrencyName) {
		ExchangeRate exchangeRate = null;

		try {

			exchangeRate = exchangeRateService.getExchangeRate(originalCurrencyName, targetCurrencyName);

		} catch(Exception ex){
			System.out.println("ExchangeRateController getExchangeRate throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			if (exchangeRate != null) {
				return new ResponseEntity<>(exchangeRate, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

	@GetMapping("currencies/from/{originalCurrencyName}/to/{targetCurrencyName}/amount/{amount}")
	public ResponseEntity<BigDecimal> getExchangedAmount(@PathVariable String originalCurrencyName,
														 @PathVariable String targetCurrencyName,
														 @PathVariable String amount) {

		BigDecimal exchangedAmount = null;
		try {
			BigDecimal exchangeAmount = new BigDecimal(amount);
			exchangedAmount = exchangeRateService.getExchangedAmount(originalCurrencyName,
					targetCurrencyName, exchangeAmount);

		} catch(Exception ex){
			System.out.println("ExchangeRateController getExchangeAmount throws exception: " + ex.getMessage());
			ex.printStackTrace();

		} finally {
			if (exchangedAmount != null) {
				return new ResponseEntity<>(exchangedAmount, HttpStatus.OK);

			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

	}

}
