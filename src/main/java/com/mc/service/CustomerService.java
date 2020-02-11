package com.mc.service;

import com.mc.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
	public abstract List<Customer> getAllCustomers();
	public abstract Customer createCustomer(Customer customer);
	public abstract void deleteCustomerById(String id);
	public abstract void deleteAllCustomers();
	public abstract List<Customer> findByAge(int age);
	public abstract Customer updateCustomer(String id, Customer customer);

}
