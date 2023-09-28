package com.ducky.expensetracker.configuration;

import com.ducky.expensetracker.repository.MongoDbExpenseRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = MongoDbExpenseRepository.class)
public class MongoDBConfiguration {
}
