package com.ducky.workouttracker.repository;

import com.ducky.workouttracker.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoUserRepository extends MongoRepository<User, String> {
}
