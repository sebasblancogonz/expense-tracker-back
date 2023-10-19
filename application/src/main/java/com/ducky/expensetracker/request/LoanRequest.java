package com.ducky.expensetracker.request;

import com.ducky.expensetracker.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @NonNull
    private Loan loan;

}
