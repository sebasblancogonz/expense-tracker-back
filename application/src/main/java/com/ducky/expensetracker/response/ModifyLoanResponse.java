package com.ducky.expensetracker.response;

import com.ducky.expensetracker.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyLoanResponse {

    private Loan updatedLoan;

}
