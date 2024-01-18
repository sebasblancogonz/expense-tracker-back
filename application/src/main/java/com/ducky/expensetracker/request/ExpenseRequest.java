package com.ducky.expensetracker.request;

import com.ducky.expensetracker.model.Expense;
import com.ducky.expensetracker.model.ExpenseCategory;
import com.ducky.expensetracker.model.Participant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequest {

    @NotNull(message = "Provide a valid expense description")
    private String description;
    @NotNull(message = "Provide a valid expense amount")
    private BigDecimal amount;
    @NotNull(message = "Provide a valid expense category")
    private ExpenseCategory category;
    @JsonFormat(pattern = "MM/dd/yyyy")
    @NotNull(message = "Provide a valid expense date")
    private LocalDate date;
    private List<Participant> participants;

}
