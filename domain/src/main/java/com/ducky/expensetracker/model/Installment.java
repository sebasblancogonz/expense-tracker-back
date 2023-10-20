package com.ducky.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Installment {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private BigDecimal amount;
    private BigDecimal interest;
    private BigDecimal redeemed;
    private BigDecimal totalRedeemed;
    private Integer remainingInstallments;
    private BigDecimal remainingAmount;

    public Installment() {
    }

    public Installment(LocalDate date, BigDecimal amount, BigDecimal interest, BigDecimal redeemed,
                       BigDecimal totalRedeemed, Integer remainingInstallments, BigDecimal remainingAmount) {
        this.date = date;
        this.amount = amount;
        this.interest = interest;
        this.redeemed = redeemed;
        this.totalRedeemed = totalRedeemed;
        this.remainingInstallments = remainingInstallments;
        this.remainingAmount = remainingAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getRedeemed() {
        return redeemed;
    }

    public void setRedeemed(BigDecimal redeemed) {
        this.redeemed = redeemed;
    }

    public BigDecimal getTotalRedeemed() {
        return totalRedeemed;
    }

    public void setTotalRedeemed(BigDecimal totalRedeemed) {
        this.totalRedeemed = totalRedeemed;
    }

    public Integer getRemainingInstallments() {
        return remainingInstallments;
    }

    public void setRemainingInstallments(Integer remainingInstallments) {
        this.remainingInstallments = remainingInstallments;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
}
