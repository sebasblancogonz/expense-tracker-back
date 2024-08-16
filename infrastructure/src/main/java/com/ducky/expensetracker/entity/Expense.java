package com.ducky.expensetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Document("expenses")
@NoArgsConstructor
public class Expense {

    @Id
    private String id;
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private ExpenseCategory category;
    private RecurrentData recurrentData;
    private List<Participant> participants;


    public Expense(String description, BigDecimal amount, LocalDate date, List<Participant> participants) {
        super();
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.participants = participants;
    }

}
