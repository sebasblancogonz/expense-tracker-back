package com.ducky.expensetracker.service.impl;

import com.ducky.expensetracker.model.Expense;
import com.ducky.expensetracker.model.ExpenseCategory;
import com.ducky.expensetracker.repository.ExpenseRepository;
import com.ducky.expensetracker.service.ExpenseService;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public final class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(final ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public String addExpense(Expense expense) {
        // Check if the expense has participants, if it has participants, then share the expense amount between them
        if (expense.getParticipants() != null && !expense.getParticipants().isEmpty()) {
            BigDecimal amountPerParticipant = expense.getAmount().divide(BigDecimal.valueOf(expense.getParticipants().size()), RoundingMode.HALF_EVEN);
            expense.getParticipants().forEach(participant -> participant.setAmount(amountPerParticipant));
        }
        return expenseRepository.addExpense(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.getAllExpenses();
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
        return getExpensesCurrentMonth()
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Expense> getExpensesToCurrentDate() {
        return getExpensesCurrentMonth();
    }

    @Override
    public BigDecimal calculateExpensesToCurrentDateByCategory(String category) {
        Pair<LocalDate, LocalDate> dates = getCurrentMonthDates();
        ExpenseCategory expenseCategory = ExpenseCategory.valueOf(category);
        return expenseRepository.searchAllExpensesBetweenDatesByCategory(dates.getLeft(), dates.getRight(), expenseCategory)
                .stream()
                .filter(expense -> expense.getCategory().name().equals(category))
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Expense> getExpensesCurrentMonth() {
        Pair<LocalDate, LocalDate> dates = getCurrentMonthDates();
        return expenseRepository.searchAllExpensesBetweenDates(dates.getLeft(),
                dates.getRight());
    }

    private Pair<LocalDate, LocalDate> getCurrentMonthDates() {
        return Pair.of(LocalDate.now().withDayOfMonth(1), LocalDate.now().atTime(LocalTime.MAX).toLocalDate());
    }

}
