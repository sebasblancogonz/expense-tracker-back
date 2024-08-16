package com.ducky.expensetracker.model;


import com.fasterxml.jackson.annotation.JsonFormat;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Expense {

    private String id;
    private String description;
    private BigDecimal amount;
    private ExpenseCategory category;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private RecurrentData recurrentData;
    List<Participant> participants;

    public Expense() {

    }

    public Expense(String description, BigDecimal amount, Integer dayOfMonth, ExpenseCategory category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.recurrentData = new RecurrentData(dayOfMonth);
    }

    public Expense(String id, String description, BigDecimal amount, Integer dayOfMonth, ExpenseCategory category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.recurrentData = new RecurrentData(dayOfMonth);
    }

    public Expense(String description, BigDecimal amount, LocalDate date, ExpenseCategory category, List<Participant> participants) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.participants = participants;
    }

    public Expense(String id, String description, BigDecimal amount, LocalDate date, ExpenseCategory category, List<Participant> participants) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.participants = participants;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public RecurrentData getRecurrentData() {
        return recurrentData;
    }

    public void setRecurrentData(RecurrentData recurrentData) {
        this.recurrentData = recurrentData;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

}