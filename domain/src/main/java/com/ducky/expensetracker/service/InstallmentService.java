package com.ducky.expensetracker.service;

import com.ducky.expensetracker.model.Installment;

import java.util.List;

public interface InstallmentService {

    String addInstallment(Installment expense);

    List<String> addInstallments(List<Installment> expense);

    Installment searchInstallment(String userId);

    List<Installment> getAllInstallments();

    Installment updateInstallment(Installment expense);

}
