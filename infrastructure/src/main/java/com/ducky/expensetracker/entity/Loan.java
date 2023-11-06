package com.ducky.expensetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
@Document("loans")
@NoArgsConstructor
public class Loan {

    private String id;
    private String description;
    private LocalDate startDate;
    private LocalDate finishDate;
    private BigDecimal interest;
    private BigDecimal totalAmount;
    private BigDecimal interestTotalAmount;
    private BigDecimal monthlyAmount;
    private BigDecimal totalRedeemed;
    private Integer remainingInstallments;
    private List<Installment> installments;

    public Loan(LocalDate startDate, LocalDate finishDate,
                BigDecimal interest, BigDecimal monthlyAmount, String description,
                BigDecimal totalAmount, BigDecimal interestTotalAmount, List<Installment> installments) {
        super();
        this.description = description;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.interest = interest;
        this.monthlyAmount = monthlyAmount;
        this.totalAmount = totalAmount;
        this.interestTotalAmount = interestTotalAmount;
        this.installments = installments;
    }

}
