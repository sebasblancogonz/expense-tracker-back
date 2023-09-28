package com.ducky.expensetracker.request;

import com.ducky.expensetracker.model.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequest {

    @NonNull
    private Expense expense;

}
