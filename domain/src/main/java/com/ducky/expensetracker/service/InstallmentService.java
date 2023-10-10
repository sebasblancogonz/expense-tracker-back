package com.ducky.expensetracker.service;

import com.ducky.expensetracker.model.Installment;

import java.util.List;

public interface InstallmentService {

    String addInstallment(Installment installment);

    List<String> addInstallments(List<Installment> installments);

    Installment searchInstallment(String userId);

    List<Installment> getAllInstallments();

    Double getMonthlyTotal();

    Installment updateInstallment(Installment installment, String installmentId);

}
