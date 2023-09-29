package com.ducky.expensetracker.request;

import com.ducky.expensetracker.model.Expense;
import com.ducky.expensetracker.model.Installment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentRequest {

    @NonNull
    private Installment installment;

}
