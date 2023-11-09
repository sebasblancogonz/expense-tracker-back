package com.ducky.expensetracker.service.impl;

import com.ducky.expensetracker.model.Expense;
import com.ducky.expensetracker.repository.ExpenseRepository;
import com.ducky.expensetracker.service.ExpenseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record ExpenseServiceImpl(ExpenseRepository expenseRepository) implements ExpenseService {

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

    @Override
    public BigDecimal calculateExpensesToCurrentDate() {
        return expenseRepository.searchAllExpensesBetweenDates(LocalDate.now().withDayOfMonth(1),
                        LocalDate.now().atTime(LocalTime.MAX).toLocalDate())
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
