package com.ducky.expensetracker.repository;

import com.ducky.expensetracker.entity.Expense;
import com.ducky.expensetracker.entity.ExpenseCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MongoExpenseRepository extends MongoRepository<Expense, String> {

    Expense findByDescription(String description);

    List<Expense> findAllByPaymentDateBetween(LocalDate startDate, LocalDate endDate);

    List<Expense> findAllByPaymentDateBetweenAndCategory(LocalDate startDate, LocalDate endDate, ExpenseCategory category);

}
