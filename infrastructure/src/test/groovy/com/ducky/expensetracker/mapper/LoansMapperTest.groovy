package com.ducky.expensetracker.mapper

import com.ducky.expensetracker.model.Installment
import com.ducky.expensetracker.model.Loan
import spock.lang.Specification

import java.time.LocalDate

class LoansMapperTest extends Specification {

    private LoansMapper loansMapper

    def setup() {
        loansMapper = new LoansMapperImpl()
    }

    def "ToEntity"() {
        given:
        def loan = buildLoanModel()

        when:
        def result = loansMapper.toEntity(loan)

        then:
        assert result.id == loan.id
        assert result.totalRedeemed == loan.totalRedeemed
        assert result.description == loan.description
        assert result.totalAmount == loan.totalAmount
        assert result.startDate == loan.startDate
        assert result.finishDate == loan.finishDate
        assert result.interestTotalAmount == loan.interestTotalAmount
        assert result.monthlyAmount == loan.monthlyAmount
        assert result.totalRedeemed == loan.totalRedeemed
        assert result.remainingInstallments == loan.remainingInstallments
        assert result.installments.size() == loan.installments.size()
    }

    def "ToModel"() {
        given:
        def loan = buildLoanEntity()

        when:
        def result = loansMapper.toModel(loan)

        then:
        assert result.id == loan.id
        assert result.totalRedeemed == loan.totalRedeemed
        assert result.description == loan.description
        assert result.totalAmount == loan.totalAmount
        assert result.startDate == loan.startDate
        assert result.finishDate == loan.finishDate
        assert result.interestTotalAmount == loan.interestTotalAmount
        assert result.monthlyAmount == loan.monthlyAmount
        assert result.totalRedeemed == loan.totalRedeemed
        assert result.remainingInstallments == loan.remainingInstallments
        assert result.installments.size() == loan.installments.size()
    }

    def "ToModelList"() {
        given:
        def loans = [buildLoanEntity()]

        when:
        def result = loansMapper.toModelList(loans)

        then:
        assert result.size() == loans.size()
    }

    def "ToEntityList"() {
        given:
        def loans = [buildLoanModel()]

        when:
        def result = loansMapper.toEntityList(loans)

        then:
        assert result.size() == loans.size()
    }

    def buildLoanModel() {
        Loan loan = new Loan()
        loan.totalRedeemed = 100.0
        loan.description = "Test"
        loan.totalAmount = 100.0
        loan.startDate = LocalDate.of(2020, 1, 1)
        loan.finishDate = LocalDate.of(2020, 1, 1)
        loan.interestTotalAmount = 10.0
        loan.monthlyAmount = 10.0
        loan.totalRedeemed = 10.0
        loan.remainingInstallments = 10
        loan.installments = [buildInstallmentModel()]
        return loan
    }

    def buildLoanEntity() {
        com.ducky.expensetracker.entity.Loan loan = new com.ducky.expensetracker.entity.Loan()
        loan.totalRedeemed = 100.0
        loan.description = "Test"
        loan.totalAmount = 100.0
        loan.startDate = LocalDate.of(2020, 1, 1)
        loan.finishDate = LocalDate.of(2020, 1, 1)
        loan.interestTotalAmount = 10.0
        loan.monthlyAmount = 10.0
        loan.totalRedeemed = 10.0
        loan.remainingInstallments = 10
        loan.installments = [buildInstallmentEntity()]
        return loan
    }

    def buildInstallmentModel() {
        Installment installment = new Installment()
        installment.amount = 100
        installment.totalRedeemed = 100.0
        installment.date = LocalDate.of(2020, 1, 1)
        return installment
    }

    def buildInstallmentEntity() {
        com.ducky.expensetracker.entity.Installment installment = new com.ducky.expensetracker.entity.Installment()
        installment.amount = 100
        installment.totalRedeemed = 100.0
        installment.date = LocalDate.of(2020, 1, 1)
        return installment
    }

}
