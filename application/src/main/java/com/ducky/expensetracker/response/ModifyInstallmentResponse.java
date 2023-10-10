package com.ducky.expensetracker.response;

import com.ducky.expensetracker.model.Installment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyInstallmentResponse {

    private Installment updatedInstallment;

}
