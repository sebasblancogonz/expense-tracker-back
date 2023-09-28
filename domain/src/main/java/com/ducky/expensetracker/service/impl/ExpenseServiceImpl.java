package com.ducky.expensetracker.service.impl;

import com.ducky.expensetracker.model.Expense;
import com.ducky.expensetracker.repository.ExpenseRepository;
import com.ducky.expensetracker.service.ExpenseService;

public record ExpenseServiceImpl(
        ExpenseRepository expenseRepository) implements ExpenseService {

    @Override
    public String addExpense(Expense expense) {
        return expenseRepository.addExpense(expense);
    }

    @Override
    public Expense searchExpense(String expenseId) {
        return expenseRepository.searchExpense(expenseId);
    }

    @Override
    public Expense updateExpense(Expense expense) {
        return null;
    }
}
