package com.ducky.expensetracker.configuration;

import com.ducky.expensetracker.repository.MongoDbExpenseRepository;
import com.ducky.expensetracker.repository.MongoDbInstallmentRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {MongoDbExpenseRepository.class, MongoDbInstallmentRepository.class})
public class MongoDBConfiguration {
}
