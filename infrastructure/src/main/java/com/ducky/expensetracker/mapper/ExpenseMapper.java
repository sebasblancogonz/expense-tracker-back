package com.ducky.expensetracker.mapper;

import com.ducky.expensetracker.entity.Expense;
import org.mapstruct.Mapper;

@Mapper
public interface ExpenseMapper {

    Expense toEntity(com.ducky.expensetracker.model.Expense expenseModel);

    com.ducky.expensetracker.model.Expense toModel(Expense expenseEntity);


}
