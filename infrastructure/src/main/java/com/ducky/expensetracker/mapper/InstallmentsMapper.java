package com.ducky.expensetracker.mapper;

import com.ducky.expensetracker.entity.Installment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface InstallmentsMapper {

    Installment toEntity(com.ducky.expensetracker.model.Installment installmentModel);

    com.ducky.expensetracker.model.Installment toModel(Installment installmentEntity);
    List<com.ducky.expensetracker.model.Installment> toModelList(List<Installment> installmentEntity);

    List<Installment> toEntityList(List<com.ducky.expensetracker.model.Installment> installmentModels);

}
