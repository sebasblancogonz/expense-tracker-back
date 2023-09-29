package com.ducky.expensetracker.service.impl;

import com.ducky.expensetracker.model.Installment;
import com.ducky.expensetracker.repository.InstallmentRepository;
import com.ducky.expensetracker.service.InstallmentService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

public record InstallmentServiceImpl(InstallmentRepository installmentRepository) implements InstallmentService {

    @Override
    public String addInstallment(Installment installment) {
        calculateRemainingData(installment);
        return installmentRepository.addInstallment(installment);
    }

    @Override
    public List<String> addInstallments(List<Installment> installments) {
        installments.forEach(this::calculateRemainingData);
        return installmentRepository.addInstallments(installments);
    }

    @Override
    public Installment searchInstallment(String installmentId) {
        return installmentRepository.searchInstallment(installmentId);
    }

    @Override
    public List<Installment> getAllInstallments() {
        return installmentRepository.getAllInstallments();
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
        return getMonthsBetween(LocalDate.now(), finishDate);
    }

    private double calculateRemainingAmount(Installment installment) {
        return installment.getTotalAmount() - getAmountPaid(installment.getMonthlyAmount(), installment.getStartDate());
    }

    private double getAmountPaid(double installmentAmount, LocalDate startDate) {
        return  getMonthsPaid(startDate) * installmentAmount;
    }

    private static int getMonthsPaid(LocalDate startDate) {
        return getMonthsBetween(startDate, LocalDate.now());
    }

    private static int getMonthsBetween(LocalDate start, LocalDate end) {
        return (int) ChronoUnit.MONTHS.between(YearMonth.from(start), YearMonth.from(end));
    }

}
