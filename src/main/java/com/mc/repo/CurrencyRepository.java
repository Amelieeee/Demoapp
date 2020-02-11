package com.mc.repo;

import com.mc.model.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CurrencyRepository extends MongoRepository<Currency, String>{
	List<Currency> findByName(String name);
}
