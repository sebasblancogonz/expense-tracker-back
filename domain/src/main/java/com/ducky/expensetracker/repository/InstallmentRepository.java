package com.ducky.expensetracker.repository;

import com.ducky.expensetracker.model.Installment;

import java.util.List;

public interface InstallmentRepository {

    Installment searchInstallment(String installmentId);

    List<Installment> getAllInstallments();

    String addInstallment(Installment installment);

    List<String> addInstallments(List<Installment> installments);

    Installment modifyInstallment(Installment installment);

}
