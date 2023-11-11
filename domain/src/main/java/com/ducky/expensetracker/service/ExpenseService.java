package com.ducky.expensetracker.service;

import com.ducky.expensetracker.model.Expense;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {

    String addExpense(Expense expense);

    Expense searchExpense(String userId);

    Expense updateExpense(Expense expense);

    BigDecimal calculateExpensesToCurrentDate();

    List<Expense> getExpensesToCurrentDate();

}
