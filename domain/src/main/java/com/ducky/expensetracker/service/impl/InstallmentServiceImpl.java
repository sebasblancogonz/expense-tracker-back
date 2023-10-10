package com.ducky.expensetracker.service.impl;

import com.ducky.expensetracker.model.Installment;
import com.ducky.expensetracker.repository.InstallmentRepository;
import com.ducky.expensetracker.service.InstallmentService;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public Double getMonthlyTotal() {
        return getDoubleWithTwoDecimals(calculateMonthlyTotal());
    }

    @Override
    public Installment updateInstallment(Installment installment, String installmentId) {
        calculateRemainingData(installment);
        return installmentRepository.modifyInstallment(installment, installmentId);
    }

    private double calculateMonthlyTotal() {
        return installmentRepository.getAllInstallments()
                .stream()
                .mapToDouble(Installment::getMonthlyAmount)
                .sum();
    }

    private void calculateRemainingData(Installment installment) {
        LocalDate finishDate = installment.getFinishDate();
        int remainingInstallments = calculateRemainingInstallments(finishDate);
        double remainingAmount = calculateRemainingAmount(installment);
        installment.setRemainingInstallments(remainingInstallments);
        installment.setRemainingAmount(getDoubleWithTwoDecimals(remainingAmount));
    }

    private int calculateRemainingInstallments(LocalDate finishDate) {
        return getMonthsBetween(LocalDate.now(), finishDate);
    }

    private double calculateRemainingAmount(Installment installment) {
        return installment.getTotalAmount() - getAmountPaid(installment.getMonthlyAmount(), installment.getStartDate());
    }

    private double getAmountPaid(double installmentAmount, LocalDate startDate) {
        return getMonthsPaid(startDate) * installmentAmount;
    }

    private static int getMonthsPaid(LocalDate startDate) {
        return getMonthsBetween(startDate, LocalDate.now());
    }

    private static int getMonthsBetween(LocalDate start, LocalDate end) {
        return (int) ChronoUnit.MONTHS.between(YearMonth.from(start), YearMonth.from(end));
    }

    private double getDoubleWithTwoDecimals(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
