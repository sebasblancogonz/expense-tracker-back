package com.ducky.expensetracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class RecurrentData {

    private Integer dayOfMonth;

}
