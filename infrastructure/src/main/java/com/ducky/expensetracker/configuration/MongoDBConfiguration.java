package com.ducky.expensetracker.configuration;

import com.ducky.expensetracker.repository.MongoDbExpenseRepository;
import com.ducky.expensetracker.repository.MongoDbLoanRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {MongoDbExpenseRepository.class, MongoDbLoanRepository.class})
public class MongoDBConfiguration {
}
