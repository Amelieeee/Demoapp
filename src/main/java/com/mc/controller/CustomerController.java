package com.mc.controller;

import com.mc.model.Customer;
import com.mc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@PostMapping("/customers/create")
	public Customer postCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id) {
		customerService.deleteCustomerById(id);
		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/customers/delete")
	public ResponseEntity<String> deleteAllCustomers() {
		customerService.deleteAllCustomers();
		return new ResponseEntity<>("All customers have been deleted!", HttpStatus.OK);
	}

	@GetMapping("customers/age/{age}")
	public List<Customer> findByAge(@PathVariable int age) {
		return customerService.findByAge(age);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {

		Customer _customer = customerService.updateCustomer(id, customer);

		if (_customer != null) {
			return new ResponseEntity<>(_customer, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
