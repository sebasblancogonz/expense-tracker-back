package com.ducky.expensetracker.model;

import java.math.BigDecimal;

public class Participant {

    private String name;
    private BigDecimal amount;
    private boolean hasPaid;

    public Participant() {

    }

    public Participant(String name, BigDecimal amount, boolean hasPaid) {
        this.name = name;
        this.amount = amount;
        this.hasPaid = hasPaid;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isHasPaid() {
        return hasPaid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setHasPaid(boolean hasPaid) {
        this.hasPaid = hasPaid;
    }

}
