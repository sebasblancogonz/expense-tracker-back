package com.ducky.expensetracker.mapper;

import com.ducky.expensetracker.entity.Installment;
import com.ducky.expensetracker.entity.Loan;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LoansMapper {

    Loan toEntity(com.ducky.expensetracker.model.Loan loanModel);

    com.ducky.expensetracker.model.Loan toModel(Loan loanEntity);
    List<com.ducky.expensetracker.model.Loan> toModelList(List<Loan> loanEntity);

    List<Loan> toEntityList(List<com.ducky.expensetracker.model.Loan> loanModels);

}
