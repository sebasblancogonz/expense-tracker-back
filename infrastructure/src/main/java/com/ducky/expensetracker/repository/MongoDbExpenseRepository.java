package com.ducky.expensetracker.repository;


import com.ducky.expensetracker.mapper.ExpensesMapper;
import com.ducky.expensetracker.model.Expense;
import com.ducky.expensetracker.model.ExpenseCategory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Primary
public class MongoDbExpenseRepository implements ExpenseRepository {

    private final MongoExpenseRepository repository;
    private final ExpensesMapper expensesMapper;

    public MongoDbExpenseRepository(final MongoExpenseRepository repository, final ExpensesMapper expensesMapper) {
        this.repository = repository;
        this.expensesMapper = expensesMapper;
    }

    @Override
    public Expense searchExpense(String expenseId) {
        Optional<com.ducky.expensetracker.entity.Expense> userEntity = repository.findById(expenseId);
        return userEntity.map(expensesMapper::toModel).orElse(null);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expensesMapper.toModelList(repository.findAll());
    }

    @Override
    public String addExpense(Expense expense) {
        com.ducky.expensetracker.entity.Expense expenseEntity = expensesMapper.toEntity(expense);
        return repository.save(expenseEntity).getId();
    }

    @Override
    public Expense modifyExpense(Expense expense) {
        return null;
    }

    @Override
    public List<Expense> searchAllExpensesBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<com.ducky.expensetracker.entity.Expense> expenses = repository.findAllByPaymentDateBetween(startDate, endDate);
        return expensesMapper.toModelList(expenses);
    }

    @Override
    public List<Expense> searchAllExpensesBetweenDatesByCategory(LocalDate startDate, LocalDate endDate, ExpenseCategory category) {
        com.ducky.expensetracker.entity.ExpenseCategory expenseCategory = com.ducky.expensetracker.entity.ExpenseCategory.valueOf(category.name());
        List<com.ducky.expensetracker.entity.Expense> expenses = repository.findAllByPaymentDateBetweenAndCategory(startDate, endDate, expenseCategory);
        return expensesMapper.toModelList(expenses);
    }

}
