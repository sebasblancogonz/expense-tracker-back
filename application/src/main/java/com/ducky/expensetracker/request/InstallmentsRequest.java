package com.ducky.expensetracker.request;

import com.ducky.expensetracker.model.Installment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstallmentsRequest {

    @NonNull
    private List<Installment> installments;

}
