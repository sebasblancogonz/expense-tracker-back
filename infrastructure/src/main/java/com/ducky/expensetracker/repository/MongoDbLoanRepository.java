package com.ducky.expensetracker.repository;


import com.ducky.expensetracker.entity.Loan;
import com.ducky.expensetracker.mapper.LoansMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

@Component
@Primary
public class MongoDbLoanRepository implements LoanRepository {

    private final MongoLoanRepository repository;
    private final LoansMapper loansMapper;

    public MongoDbLoanRepository(final MongoLoanRepository repository, final LoansMapper loansMapper) {
        this.repository = repository;
        this.loansMapper = loansMapper;
    }

    @Override
    public com.ducky.expensetracker.model.Loan searchLoan(String loanId) {
        Optional<Loan> loanEntity = repository.findById(loanId);
        return loanEntity.map(loansMapper::toModel).orElse(null);
    }

    @Override
    public List<com.ducky.expensetracker.model.Loan> getAllLoans() {
        return loansMapper.toModelList(repository.findAll());
    }

    @Override
    public String addLoan(com.ducky.expensetracker.model.Loan loan) {
        Loan loanEntity = loansMapper.toEntity(loan);
        return repository.save(loanEntity).getId();
    }

    @Override
    public List<String> addLoans(List<com.ducky.expensetracker.model.Loan> loans) {
        List<Loan> installmentEntities = loansMapper.toEntityList(loans);
        return repository.saveAll(installmentEntities).stream().map(Loan::getId).toList();
    }

    @Override
    public com.ducky.expensetracker.model.Loan updateLoan(com.ducky.expensetracker.model.Loan loan,
                                                                        String installmentId) {
        Optional<Loan> oldInstallment = repository.findById(installmentId);
        Optional<Loan> updatedInstallment = updateLoan(loansMapper.toEntity(loan), oldInstallment);
        return updatedInstallment.map(repository::save).map(loansMapper::toModel).orElse(null);
    }


    public static Optional<Loan> updateLoan(Loan newInstallment, Optional<Loan> oldInstallment) {
        return oldInstallment.map(loan -> {
            Loan updatedInstallment = new Loan();

            updateIfNotNull(newInstallment.getDescription(), updatedInstallment::setDescription);
            updateIfNotNull(newInstallment.getStartDate(), updatedInstallment::setStartDate);
            updateIfNotNull(newInstallment.getFinishDate(), updatedInstallment::setFinishDate);
            updateIfNonZero(newInstallment.getInterest(), updatedInstallment::setInterest);
            updateIfNonZero(newInstallment.getTotalAmount(), updatedInstallment::setTotalAmount);
            updateIfNonZero(newInstallment.getInterestTotalAmount(), updatedInstallment::setInterestTotalAmount);
            updateIfNonZero(newInstallment.getMonthlyAmount(), updatedInstallment::setMonthlyAmount);
            updateIfNonZero(newInstallment.getTotalRedeemed(), updatedInstallment::setTotalRedeemed);
            updateIfNonZero(newInstallment.getRemainingInstallments(), updatedInstallment::setRemainingInstallments);
            updateIfNotEmpty(newInstallment.getInstallments(), updatedInstallment::setInstallments);

            return updatedInstallment;
        });
    }

    private static void updateIfNotEmpty(List<com.ducky.expensetracker.entity.Installment> installments, Consumer<List<com.ducky.expensetracker.entity.Installment>> setInstallments) {
        if (installments != null && !installments.isEmpty()) {
            setInstallments.accept(installments);
        }
    }

    private static void updateIfNotNull(LocalDate value, Consumer<LocalDate> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private static void updateIfNonZero(BigDecimal value, Consumer<BigDecimal> setter) {
        if (!BigDecimal.ZERO.equals(value)) {
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
