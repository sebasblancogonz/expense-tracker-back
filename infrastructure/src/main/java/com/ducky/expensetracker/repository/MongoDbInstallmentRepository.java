package com.ducky.expensetracker.repository;


import com.ducky.expensetracker.entity.Installment;
import com.ducky.expensetracker.mapper.InstallmentsMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public com.ducky.expensetracker.model.Installment searchInstallment(String installmentId) {
        Optional<Installment> installmentEntity = repository.findById(installmentId);
        return installmentEntity.map(installmentsMapper::toModel).orElse(null);
    }

    @Override
    public List<com.ducky.expensetracker.model.Installment> getAllInstallments() {
        return installmentsMapper.toModelList(repository.findAll());
    }

    @Override
    public String addInstallment(com.ducky.expensetracker.model.Installment expense) {
        Installment expenseEntity = installmentsMapper.toEntity(expense);
        return repository.save(expenseEntity).getId();
    }

    @Override
    public List<String> addInstallments(List<com.ducky.expensetracker.model.Installment> installments) {
        List<Installment> installmentEntities = installmentsMapper.toEntityList(installments);
        return repository.saveAll(installmentEntities).stream().map(Installment::getId).toList();
    }

    @Override
    public com.ducky.expensetracker.model.Installment modifyInstallment(com.ducky.expensetracker.model.Installment installment) {
        return null;
    }
}
