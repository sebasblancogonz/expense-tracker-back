package com.ducky.expensetracker.mapper;

import com.ducky.expensetracker.entity.Expense;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ExpensesMapper {

    Expense toEntity(com.ducky.expensetracker.model.Expense expenseModel);

    com.ducky.expensetracker.model.Expense toModel(Expense expenseEntity);

    List<com.ducky.expensetracker.model.Expense> toModelList(List<Expense> expenseEntityList);

}