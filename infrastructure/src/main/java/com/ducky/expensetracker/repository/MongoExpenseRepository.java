package com.ducky.expensetracker.repository;

import com.ducky.expensetracker.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoExpenseRepository extends MongoRepository<Expense, String> {

    Expense findByDescription(String description);

}
