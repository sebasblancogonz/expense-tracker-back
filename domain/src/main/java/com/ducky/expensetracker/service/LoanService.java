package com.ducky.expensetracker.service;

import com.ducky.expensetracker.model.Loan;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {

    String addLoan(Loan loan);

    List<String> addLoans(List<Loan> installments);

    Loan searchLoan(String userId);

    List<Loan> getAllLoans();

    BigDecimal getMonthlyTotal();

    Loan updateLoan(Loan loan, String installmentId);

}
