package com.ducky.expensetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;

@Data
@Document("installments")
@NoArgsConstructor
public class Installment {

    @Id
    private String id;
    private String description;
    private String expenseId;
    private LocalDate startDate;
    private LocalDate finishDate;
    private Integer remainingInstallments;
    private Double interest;
    private double monthlyAmount;
    private double remainingAmount;
    private double totalAmount;

    public Installment(String expenseId, LocalDate startDate, LocalDate finishDate, Integer remainingInstallments,
                       Double interest, double monthlyAmount, double remainingAmount, String description,
                       double totalAmount) {
        super();
        this.expenseId = expenseId;
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.remainingInstallments = remainingInstallments;
        this.interest = interest;
        this.monthlyAmount = monthlyAmount;
        this.remainingAmount = remainingAmount;
        this.totalAmount = totalAmount;
    }
}
