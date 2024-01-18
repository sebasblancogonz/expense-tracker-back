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
    private LocalDate date;
    List<Participant> participants;

    public Expense() {

    }

    public Expense(String description, BigDecimal amount, LocalDate date, ExpenseCategory category, List<Participant> participants) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.participants = participants;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

}