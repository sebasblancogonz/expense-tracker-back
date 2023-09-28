package com.ducky.expensetracker.service;

import com.ducky.expensetracker.model.Expense;

public interface ExpenseService {

    String addExpense(Expense expense);

    Expense searchExpense(String userId);

    Expense updateExpense(Expense expense);

}
