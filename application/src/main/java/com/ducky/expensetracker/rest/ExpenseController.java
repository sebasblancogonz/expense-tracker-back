package com.ducky.expensetracker.rest;

import com.ducky.expensetracker.model.Expense;
import com.ducky.expensetracker.request.ExpenseRequest;
import com.ducky.expensetracker.response.AddExpenseResponse;
import com.ducky.expensetracker.response.GetExpensesResponse;
import com.ducky.expensetracker.service.ExpenseService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(final ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddExpenseResponse> addExpense(@RequestBody final ExpenseRequest expenseRequest) {
        final Expense expense = new Expense(expenseRequest.getDescription(), expenseRequest.getAmount(), expenseRequest.getDate(), expenseRequest.getCategory(), expenseRequest.getParticipants());
        final String expenseId = expenseService.addExpense(expense);
       return ResponseEntity.ok(AddExpenseResponse.builder().id(expenseId).build());
    }

    @PostMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddExpenseResponse> addExpenseForToday(@RequestBody final ExpenseRequest expenseRequest) {
        final Expense expense = new Expense(expenseRequest.getDescription(), expenseRequest.getAmount(), LocalDate.now(), expenseRequest.getCategory(), expenseRequest.getParticipants());
        final String expenseId = expenseService.addExpense(expense);
        return ResponseEntity.ok(AddExpenseResponse.builder().id(expenseId).build());
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Expense> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping(value = "/{expenseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Expense searchUser(@PathVariable final String expenseId) {
        return expenseService.searchExpense(expenseId);
    }

    @DeleteMapping(value = "/{expenseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void removeExpense(@PathVariable final String expenseId) {
        expenseService.removeExpense(expenseId);
    }

    @GetMapping(value = "/total-current-month", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal searchUser() {
        return expenseService.calculateExpensesToCurrentDate();
    }

    @GetMapping(value = "/total-current-month/{category}", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal totalCurrentMonthByCategory(@PathVariable final String category) {
        return expenseService.calculateExpensesToCurrentDateByCategory(category);
    }

    @GetMapping(value = "/expenses-current-month", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GetExpensesResponse> getExpensesCurrentMonth() {
        final List<Expense> expenses = expenseService.getExpensesToCurrentDate();
        return ResponseEntity.ok(GetExpensesResponse.builder().expenses(expenses).build());
    }

}
