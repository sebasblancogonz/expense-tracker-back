package com.ducky.expensetracker.repository;


import com.ducky.expensetracker.mapper.ExpenseMapper;
import com.ducky.expensetracker.mapper.InstallmentsMapper;
import com.ducky.expensetracker.model.Expense;
import com.ducky.expensetracker.model.Installment;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class MongoDbInstallmentRepository implements InstallmentRepository {

    private final MongoInstallmentRepository repository;
    private final InstallmentsMapper installmentsMapper;

    public MongoDbInstallmentRepository(final MongoInstallmentRepository repository, final InstallmentsMapper installmentsMapper) {
        this.repository = repository;
        this.installmentsMapper = installmentsMapper;
    }

    @Override
    public Installment searchInstallment(String installmentId) {
        Optional<com.ducky.expensetracker.entity.Installment> installmentEntity = repository.findById(installmentId);
        return installmentEntity.map(installmentsMapper::toModel).orElse(null);
    }

    @Override
    public String addInstallment(Installment expense) {
        com.ducky.expensetracker.entity.Installment expenseEntity = installmentsMapper.toEntity(expense);
        return repository.save(expenseEntity).getId();
    }

    @Override
    public Installment modifyInstallment(Installment installment) {
        return null;
    }
}
