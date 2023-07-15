package com.ducky.workouttracker.configuration;

import com.ducky.workouttracker.repository.MongoDbUserRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = MongoDbUserRepository.class)
public class MongoDBConfiguration {
}
