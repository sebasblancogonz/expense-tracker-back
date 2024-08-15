package com.ducky.expensetracker.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @NotBlank(message = "Provide a valid loan description")
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "The loan start date is mandatory")
    private LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "The loan end date is mandatory")
    private LocalDate finishDate;

    @NotNull(message = "The loan interest is mandatory")
    private BigDecimal interest;

    @NotNull(message = "The total amount is mandatory")
    private BigDecimal totalAmount;

    @NotNull(message = "The loan entity is mandatory")
    private String entity;

}
