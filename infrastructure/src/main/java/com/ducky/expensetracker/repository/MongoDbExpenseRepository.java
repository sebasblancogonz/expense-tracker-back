package com.ducky.expensetracker.repository;


import com.ducky.expensetracker.mapper.ExpenseMapper;
import com.ducky.expensetracker.model.Expense;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class MongoDbExpenseRepository implements ExpenseRepository {

    private final MongoExpenseRepository repository;
    private final ExpenseMapper expenseMapper;

    public MongoDbExpenseRepository(final MongoExpenseRepository repository, final ExpenseMapper expenseMapper) {
        this.repository = repository;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public Expense searchExpense(String expenseId) {
        Optional<com.ducky.expensetracker.entity.Expense> userEntity = repository.findById(expenseId);
        return userEntity.map(expenseMapper::toModel).orElse(null);
    }

    @Override
    public String addExpense(Expense expense) {
        com.ducky.expensetracker.entity.Expense expenseEntity = expenseMapper.toEntity(expense);
        return repository.save(expenseEntity).getId();
    }

    @Override
    public Expense modifyExpense(Expense expense) {
        return null;
    }
}
