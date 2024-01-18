package com.ducky.expensetracker.entity


import spock.lang.Specification

import java.time.LocalDate

class ExpenseTest extends Specification {

    def "buildExpense"() {
        given:
        def amount = 100
        def description = "test"
        def date = LocalDate.of(2020, 1, 1)
        def category = ExpenseCategory.EDUCATION
        def participants = buildParticipants()

        when:
        def expense = new Expense()
        expense.category = category
        expense.amount = amount
        expense.description = description
        expense.date = date
        expense.participants = participants

        then:
        assert expense.amount == amount
        assert expense.description == description
        assert expense.date == date
        assert expense.category == category
        assert expense.participants == participants
    }

    def buildParticipants() {
        [Participant.builder()
                 .name("test")
                 .amount(BigDecimal.TEN).build(),
         Participant.builder()
                 .name("test2")
                 .amount(BigDecimal.TEN).build()]
    }
}
