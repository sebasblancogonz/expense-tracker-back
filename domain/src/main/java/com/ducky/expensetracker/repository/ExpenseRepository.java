package com.ducky.expensetracker.repository;

import com.ducky.expensetracker.model.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository {

    Expense searchExpense(String expenseId);

    String addExpense(Expense expense);

    Expense modifyExpense(Expense expense);

    List<Expense> searchAllExpensesBetweenDates(LocalDate startDate, LocalDate endDate);

}
