package com.ducky.expensetracker.request;

import com.ducky.expensetracker.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoansRequest {

    @NonNull
    private List<Loan> loans;

}
