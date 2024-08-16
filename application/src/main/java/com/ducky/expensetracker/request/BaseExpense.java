package com.ducky.expensetracker.request;

import com.ducky.expensetracker.model.ExpenseCategory;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseExpense {

    @NotNull(message = "Provide a valid expense description")
    private String description;
    @NotNull(message = "Provide a valid expense amount")
    private BigDecimal amount;
    @NotNull(message = "Provide a valid expense category")
    private ExpenseCategory category;

}
