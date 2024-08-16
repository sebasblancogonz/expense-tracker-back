package com.ducky.expensetracker.request;

import com.ducky.expensetracker.model.Participant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExpenseRequest extends BaseExpense {

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Provide a valid expense date")
    private LocalDate date;
    private List<Participant> participants;

}
