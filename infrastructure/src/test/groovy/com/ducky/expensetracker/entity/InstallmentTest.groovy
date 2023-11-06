package com.ducky.expensetracker.entity

import spock.lang.Specification

import java.time.LocalDate

class InstallmentTest extends Specification {

    def "BuildInstallment"() {
        given:
        def date = LocalDate.of(2020, 1, 1)
        def amount = 100.0
        def interest = 0.1
        def redeemed = 0.0
        def totalRedeemed = 0.0
        def remainingAmount = 100.0
        def remainingInstallments = 1


        when:
        def installment = new Installment(date, remainingInstallments, interest, amount, totalRedeemed,
                remainingAmount, redeemed)

        then:
        installment.date == date
        installment.amount == amount
        installment.interest == interest
        installment.redeemed == redeemed
        installment.totalRedeemed == totalRedeemed
        installment.remainingAmount == remainingAmount
        installment.remainingInstallments == remainingInstallments
    }

}
