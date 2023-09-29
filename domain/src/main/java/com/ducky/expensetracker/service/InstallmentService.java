package com.ducky.expensetracker.service;

import com.ducky.expensetracker.model.Installment;

public interface InstallmentService {

    String addInstallment(Installment expense);

    Installment searchInstallment(String userId);

    Installment updateInstallment(Installment expense);

}
