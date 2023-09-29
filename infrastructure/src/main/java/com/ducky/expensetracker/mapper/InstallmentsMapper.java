package com.ducky.expensetracker.mapper;

import com.ducky.expensetracker.entity.Installment;
import org.mapstruct.Mapper;

@Mapper
public interface InstallmentsMapper {

    Installment toEntity(com.ducky.expensetracker.model.Installment installmentModel);

    com.ducky.expensetracker.model.Installment toModel(Installment installmentEntity);

}
