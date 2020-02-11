package com.mc.repo;

import com.mc.model.ExchangeRate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExchangeRateRepository extends MongoRepository<ExchangeRate, String>{
	List<ExchangeRate> findByOriginalCurrencyName(String originalCurrencyName);
}
