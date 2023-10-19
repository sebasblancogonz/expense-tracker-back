package com.ducky.expensetracker.repository;

import com.ducky.expensetracker.model.Installment;
import com.ducky.expensetracker.model.Loan;

import java.util.List;

public interface LoanRepository {

    Loan searchLoan(String loanId);

    List<Loan> getAllLoans();

    String addLoan(Loan loan);

    List<String> addLoans(List<Loan> loans);

    Loan updateLoan(Loan loan, String loanId);

}
