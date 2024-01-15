package com.ducky.expensetracker.model;


import com.fasterxml.jackson.annotation.JsonFormat;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Expense {

    private String description;
    private BigDecimal amount;
    private ExpenseCategory category;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate paymentDate;

    public Expense() {

    }

    public Expense(String description, BigDecimal amount, LocalDate paymentDate, ExpenseCategory category) {
        this.description = description;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

}