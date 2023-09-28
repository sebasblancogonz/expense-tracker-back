package com.ducky.expensetracker.rest;

import com.ducky.expensetracker.model.Expense;
import com.ducky.expensetracker.request.ExpenseRequest;
import com.ducky.expensetracker.response.AddExpenseResponse;
import com.ducky.expensetracker.service.ExpenseService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(final ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddExpenseResponse> addExpense(@RequestBody final ExpenseRequest expenseRequest) {
        final String userId = expenseService.addExpense(expenseRequest.getExpense());
       return ResponseEntity.ok(AddExpenseResponse.builder().id(userId).build());
    }

    @GetMapping(value = "/{expenseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Expense searchUser(@PathVariable final String expenseId) {
        return expenseService.searchExpense(expenseId);
    }
}
