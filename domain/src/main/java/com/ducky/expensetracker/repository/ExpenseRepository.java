package com.ducky.expensetracker.repository;

import com.ducky.expensetracker.model.Expense;

public interface ExpenseRepository {

    Expense searchExpense(String expenseId);

    String addExpense(Expense expense);

    String addExpenseForToday(Expense expense);

    Expense modifyExpense(Expense expense);

}
