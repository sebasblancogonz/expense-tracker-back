package com.ducky.expensetracker.entity

import spock.lang.Specification

import java.time.LocalDate

class LoanTest extends Specification {

    def "BuildLoan"() {
        given:
        def description = "test"
        def startDate = LocalDate.of(2020, 1, 1)
        def finishDate = LocalDate.of(2020, 2, 1)
        def interest = 0.1
        def totalAmount = 100.0
        def interestTotalAmount = 10.0
        def monthlyAmount = 10.0
        def totalRedeemed = 0.0
        def remainingInstallments = 1
        def installments = buildInstalmments()

        when:
        def loan = new Loan(startDate, finishDate, interest, monthlyAmount, description, totalAmount, interestTotalAmount, buildInstalmments())

        then:
        assert loan.description == description
        assert loan.startDate == startDate
        assert loan.finishDate == finishDate
        assert loan.interest == interest
        assert loan.totalAmount == totalAmount
        assert loan.interestTotalAmount == interestTotalAmount
        assert loan.monthlyAmount == monthlyAmount
        assert loan.installments == installments
    }

    def buildInstalmments() {
        [Installment.builder()
                 .date(LocalDate.of(2020, 1, 1))
                 .amount(BigDecimal.TEN)
                 .interest(BigDecimal.ONE)
                 .redeemed(BigDecimal.ZERO)
                 .totalRedeemed(BigDecimal.ZERO)
                 .remainingAmount(BigDecimal.TEN)
                 .remainingInstallments(1).build(),
         Installment.builder()
                 .date(LocalDate.of(2020, 1, 1))
                 .amount(BigDecimal.TEN)
                 .interest(BigDecimal.ONE)
                 .redeemed(BigDecimal.ZERO)
                 .totalRedeemed(BigDecimal.ZERO)
                 .remainingAmount(BigDecimal.TEN)
                 .remainingInstallments(1).build()]
    }

}
