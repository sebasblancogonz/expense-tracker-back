package com.ducky.expensetracker.model;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

    private String id;
    private String description;
    private BigDecimal amount;
    private LocalDate paymentDate;

    public Expense() {

    }

    public Expense(String id, String description, BigDecimal amount, LocalDate paymentDate) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}