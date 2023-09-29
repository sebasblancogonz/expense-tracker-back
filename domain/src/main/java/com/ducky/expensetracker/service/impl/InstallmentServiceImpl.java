package com.ducky.expensetracker.service.impl;

import com.ducky.expensetracker.model.Installment;
import com.ducky.expensetracker.repository.InstallmentRepository;
import com.ducky.expensetracker.service.InstallmentService;


import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public record InstallmentServiceImpl(InstallmentRepository installmentRepository) implements InstallmentService {

    @Override
    public String addInstallment(Installment installment) {
        calculateRemainingData(installment);
        return installmentRepository.addInstallment(installment);
    }

    @Override
    public Installment searchInstallment(String installmentId) {
        return installmentRepository.searchInstallment(installmentId);
    }

    @Override
    public Installment updateInstallment(Installment expense) {
        return null;
    }


    private void calculateRemainingData(Installment installment) {
        LocalDate finishDate = installment.getFinishDate();
        installment.setRemainingInstallments(calculateRemainingInstallments(finishDate));
        installment.setRemainingAmount(calculateRemainingAmount(installment));
    }

    private Integer calculateRemainingInstallments(LocalDate finishDate) {
        return getMonthsBetween(LocalDate.now(), finishDate) + 1;
    }

    private double calculateRemainingAmount(Installment installment) {
        return installment.getTotalAmount() - getAmountPaid(installment.getMonthlyAmount(), installment.getStartDate());
    }

    private double getAmountPaid(double installmentAmount, LocalDate startDate) {
        return  getMonthsPaid(startDate) * installmentAmount;
    }

    private static int getMonthsPaid(LocalDate startDate) {
        return getMonthsBetween(startDate, LocalDate.now()) + 1;
    }

    private static int getMonthsBetween(LocalDate start, LocalDate end) {
        return (int) ChronoUnit.MONTHS.between(YearMonth.from(start), YearMonth.from(end));
    }

}
