package com.mc.serviceImpl;

import com.mc.model.Customer;
import com.mc.repo.CustomerRepository;
import com.mc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;

	@Override
	public List<Customer> getAllCustomers() {
		System.out.println("Get all Customers...");

		List<Customer> customers = new ArrayList<>();
		repository.findAll().forEach(customers::add);

		return customers;
	}

	@Override
	public Customer createCustomer(Customer customer) {

		Customer _customer = repository.save(new Customer(customer.getName(), customer.getAge()));
		return _customer;
	}

	@Override
	public void deleteCustomerById(String id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);
	}

	@Override
	public void deleteAllCustomers() {
		System.out.println("Delete All Customers...");

		repository.deleteAll();
	}

	@Override
	public List<Customer> findByAge(int age) {

		List<Customer> customers = repository.findByAge(age);
		return customers;
	}

	@Override
	public Customer updateCustomer(String id, Customer customer) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<Customer> customerData = repository.findById(id);

		if (customerData.isPresent()) {
			Customer _customer = customerData.get();
			_customer.setName(customer.getName());
			_customer.setAge(customer.getAge());
			_customer.setActive(customer.isActive());
			return repository.save(_customer);
		} else {
			return null;
		}
	}

}
