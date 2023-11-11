package com.ducky.expensetracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Loan {

    private String id;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate finishDate;
    private BigDecimal interest;
    private BigDecimal totalAmount;
    private BigDecimal interestTotalAmount;
    private BigDecimal monthlyAmount;
    private BigDecimal totalRedeemed;
    private Integer remainingInstallments;
    List<Installment> installments;

    public Loan() {
    }

    public Loan(String id, String description, BigDecimal interest, BigDecimal monthlyAmount,
                BigDecimal totalAmount, LocalDate startDate, LocalDate finishDate, List<Installment> installments,
                BigDecimal interestTotalAmount, BigDecimal totalRedeemed, Integer remainingInstallments) {
        this.id = id;
        this.description = description;
        this.interest = interest;
        this.monthlyAmount = monthlyAmount;
        this.totalAmount = totalAmount;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.installments = installments;
        this.interestTotalAmount = interestTotalAmount;
        this.totalRedeemed = totalRedeemed;
        this.remainingInstallments = remainingInstallments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(BigDecimal monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(List<Installment> installments) {
        this.installments = installments;
    }

    public BigDecimal getInterestTotalAmount() {
        return interestTotalAmount;
    }

    public void setInterestTotalAmount(BigDecimal interestTotalAmount) {
        this.interestTotalAmount = interestTotalAmount;
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

}
