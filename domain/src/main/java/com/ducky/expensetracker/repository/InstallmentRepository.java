package com.ducky.expensetracker.repository;

import com.ducky.expensetracker.model.Installment;

public interface InstallmentRepository {

    Installment searchInstallment(String installmentId);

    String addInstallment(Installment installment);

    Installment modifyInstallment(Installment installment);

}
