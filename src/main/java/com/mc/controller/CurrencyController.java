package com.mc.controller;

import com.mc.model.Currency;
import com.mc.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;

	@GetMapping("/currencies")
	public List<Currency> getAllCurrencies() {
		return currencyService.getAllCurrencies();
	}

	@PostMapping("/currencies/create")
	public Currency postCurrency(@RequestBody Currency currency) {
		return currencyService.createCurrency(currency);
	}

	@DeleteMapping("/currencies/{id}")
	public ResponseEntity<String> deleteCurrency(@PathVariable("id") String id) {
		currencyService.deleteCurrencyById(id);
		return new ResponseEntity<>("Currency has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/currencies/delete")
	public ResponseEntity<String> deleteAllCurrencies() {
		currencyService.deleteAllCurrencies();
		return new ResponseEntity<>("All currencies have been deleted!", HttpStatus.OK);
	}

	@GetMapping("currencies/name/{name}")
	public List<Currency> findByName(@PathVariable String name) {
		return currencyService.findByName(name);
	}


	@PutMapping("/currencies/{id}")
	public ResponseEntity<Currency> updateCurrency(@PathVariable("id") String id, @RequestBody Currency currency) {

		Currency _customer = currencyService.updateCurrency(id, currency);

		if (_customer != null) {
			return new ResponseEntity<>(_customer, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
