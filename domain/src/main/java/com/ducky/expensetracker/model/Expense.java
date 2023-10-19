package com.ducky.expensetracker.model;


import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDate;
import java.util.List;

public class Expense {

    private String id;
    private String description;
    private double amount;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;

    public Expense() {

    }

    public Expense(String id, String description, double amount, LocalDate paymentDate) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

}