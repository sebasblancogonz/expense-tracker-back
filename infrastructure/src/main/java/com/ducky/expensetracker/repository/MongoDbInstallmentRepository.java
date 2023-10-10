package com.ducky.expensetracker.repository;


import com.ducky.expensetracker.entity.Installment;
import com.ducky.expensetracker.mapper.InstallmentsMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;

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
    public com.ducky.expensetracker.model.Installment modifyInstallment(com.ducky.expensetracker.model.Installment installment,
                                                                        String installmentId) {
        Optional<Installment> oldInstallment = repository.findById(installmentId);
        Optional<Installment> updatedInstallment = updateInstallment(installment, oldInstallment);
        return updatedInstallment.map(repository::save).map(installmentsMapper::toModel).orElse(null);
    }


    public static Optional<Installment> updateInstallment(com.ducky.expensetracker.model.Installment newInstallment, Optional<Installment> oldInstallment) {
        return oldInstallment.map(installment -> {
            Installment updatedInstallment = new Installment();

            updateIfNotNull(newInstallment.getDescription(), updatedInstallment::setDescription);
            updateIfNonZero(newInstallment.getRemainingInstallments(), updatedInstallment::setRemainingInstallments);
            updateIfNonZero(newInstallment.getRemainingAmount(), updatedInstallment::setRemainingAmount);
            updateIfNonZero(newInstallment.getInterest(), updatedInstallment::setInterest);
            updateIfNotNull(newInstallment.getFinishDate(), updatedInstallment::setFinishDate);
            updateIfNotNull(newInstallment.getStartDate(), updatedInstallment::setStartDate);
            updateIfNonZero(newInstallment.getMonthlyAmount(), updatedInstallment::setMonthlyAmount);
            updateIfNonZero(newInstallment.getTotalAmount(), updatedInstallment::setTotalAmount);

            return updatedInstallment;
        });
    }

    private static void updateIfNotNull(LocalDate value, Consumer<LocalDate> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private static void updateIfNonZero(double value, DoubleConsumer setter) {
        if (value != 0) {
            setter.accept(value);
        }
    }

    private static void updateIfNonZero(Integer value, IntConsumer setter) {
        if (value != 0) {
            setter.accept(value);
        }
    }

    private static void updateIfNotNull(String value, Consumer<String> setter) {
        if (StringUtils.hasText(value)) {
            setter.accept(value);
        }
    }
}
