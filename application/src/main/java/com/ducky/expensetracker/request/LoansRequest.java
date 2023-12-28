package com.ducky.expensetracker.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoansRequest {

    @NotEmpty(message = "Provide a valid list of loans")
    private List<LoanRequest> loans;

}
