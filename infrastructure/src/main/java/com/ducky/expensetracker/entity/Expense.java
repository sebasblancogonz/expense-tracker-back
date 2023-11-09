package com.ducky.expensetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document("expenses")
@NoArgsConstructor
public class Expense {

    @Id
    private String id;
    private String description;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private ExpenseCategory category;


    public Expense(String description, BigDecimal amount, LocalDate paymentDate) {
        super();
        this.description = description;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}
