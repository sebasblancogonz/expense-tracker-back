package com.ducky.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDate;

public class Installment {

    private Integer remainingInstallments;
    private String description;
    private Double interest;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate finishDate;
    private double monthlyAmount;
    private double remainingAmount;
    private double totalAmount;

    public Installment() {
    }

    public Installment(Integer remainingInstallments, String description, Double interest, LocalDate startDate,
                       LocalDate finishDate, double monthlyAmount, double remainingAmount,
                       double totalAmount) {
        this.remainingInstallments = remainingInstallments;
        this.description = description;
        this.interest = interest;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.monthlyAmount = monthlyAmount;
        this.remainingAmount = remainingAmount;
        this.totalAmount = totalAmount;
    }

    public Integer getRemainingInstallments() {
        return remainingInstallments;
    }

    public void setRemainingInstallments(Integer remainingInstallments) {
        this.remainingInstallments = remainingInstallments;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public double getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(double monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
