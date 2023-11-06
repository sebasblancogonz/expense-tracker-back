package com.ducky.expensetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document("expenses")
@NoArgsConstructor
public class Expense {

    @Id
    private String id;
    private String description;
    private double amount;
    private LocalDate paymentDate;


    public Expense(String description, double amount, LocalDate paymentDate) {
        super();
        this.description = description;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}
