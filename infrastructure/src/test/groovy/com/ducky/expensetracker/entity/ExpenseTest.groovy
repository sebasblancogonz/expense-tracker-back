package com.ducky.expensetracker.entity


import spock.lang.Specification

import java.time.LocalDate

class ExpenseTest extends Specification {

    def "buildExpense"() {
        given:
        def amount = 100
        def description = "test"
        def date = LocalDate.of(2020, 1, 1)

        when:
        def expense = new Expense(description, amount, date)

        then:
        assert expense.amount == amount
        assert expense.description == description
        assert expense.paymentDate == date
    }

}
