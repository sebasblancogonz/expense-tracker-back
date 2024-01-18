package com.ducky.expensetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public class Participant {

    private String name;
    private BigDecimal amount;
    private boolean hasPaid;

}
