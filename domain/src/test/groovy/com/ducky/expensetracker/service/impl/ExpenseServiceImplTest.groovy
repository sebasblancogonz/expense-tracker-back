package com.ducky.expensetracker.service.impl

import com.ducky.expensetracker.model.Expense
import com.ducky.expensetracker.repository.ExpenseRepository
import spock.lang.Specification

import java.time.LocalDate

class ExpenseServiceImplTest extends Specification {

    private ExpenseRepository expenseRepository
    private ExpenseServiceImpl expenseService

    def setup() {
        expenseRepository = Mock(ExpenseRepository)
        expenseService = new ExpenseServiceImpl(expenseRepository)
    }

    def "AddExpense"() {
        given:
        Expense expenseRequest = buildExpenseRequest()
        String expectedId = "1234567890"

        when:
        String expenseId = expenseService.addExpense(expenseRequest)

        then:
        1 * expenseRepository.addExpense(expenseRequest) >> expectedId
        assert expenseId != null
    }

    def "SearchExpense"() {
        given: "An expense id"
        String expenseId = "1234567890"
        Expense expectedExpense = buildExpenseRequest()

        when: "SearchExpense is called"
        Expense expense = expenseService.searchExpense(expenseId)

        then: "The expense is returned"
        1 * expenseRepository.searchExpense(expenseId) >> expectedExpense
        assert expense != null
    }

    def "UpdateExpense"() {
        given: "An expense id"
        Expense expenseRequest = buildExpenseRequest()

        when: "UpdateExpense is called"
        Expense expense = expenseService.updateExpense(expenseRequest)

        then: "The expense is returned"
        assert expense == null
    }

    def buildExpenseRequest() {
        Expense expense = new Expense()
        expense.description = "Test Expense"
        expense.amount = 1000
        expense.paymentDate = LocalDate.of(2023, 1, 1)
        return expense
    }
}
