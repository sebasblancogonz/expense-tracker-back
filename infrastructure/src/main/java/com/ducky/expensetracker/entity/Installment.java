package com.ducky.expensetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
public class Installment {

    private LocalDate date;
    private BigDecimal amount;
    private BigDecimal interest;
    private BigDecimal redeemed;
    private BigDecimal totalRedeemed;
    private BigDecimal remainingAmount;
    private Integer remainingInstallments;

}
