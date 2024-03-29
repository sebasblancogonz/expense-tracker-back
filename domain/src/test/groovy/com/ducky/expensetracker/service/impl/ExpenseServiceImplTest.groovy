package com.ducky.expensetracker.service.impl

import com.ducky.expensetracker.model.Expense
import com.ducky.expensetracker.model.ExpenseCategory
import com.ducky.expensetracker.model.Participant
import com.ducky.expensetracker.repository.ExpenseRepository
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime

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

    def "AddExpenseWithParticipants"() {
        given:
        Expense expenseRequest = buildExpenseRequestWithParticipants()
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

    def "SearchExpenseWithParticipants"() {
        given: "An expense id"
        String expenseId = "1234567890"
        Expense expectedExpense = buildExpenseRequestWithParticipants()

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

    def "GetWastedOnCurrentMonth"() {
        given: "An stored expense"
        Expense expense = buildExpenseRequest()
        expense.date = LocalDate.now()
        List<Expense> expenses = [expense]
        BigDecimal expectedAmount = expense.amount
        LocalDate startDate = LocalDate.now().withDayOfMonth(1)
        LocalDate endDate = LocalDate.now().atTime(LocalTime.MAX).toLocalDate()

        when: "calculate expenses to current date is called"
        BigDecimal amount = expenseService.calculateExpensesToCurrentDate()

        then: "The amount is the expected"
        1 * expenseRepository.searchAllExpensesBetweenDates(startDate, endDate) >> expenses
        assert amount == expectedAmount
    }

    def "should return all the expenses for the current month"() {
        given: "An stored expense"
        Expense expense = buildExpenseRequest()
        expense.date = LocalDate.now()
        List<Expense> expenses = [expense]
        LocalDate startDate = LocalDate.now().withDayOfMonth(1)
        LocalDate endDate = LocalDate.now().atTime(LocalTime.MAX).toLocalDate()

        when: "search all expenses is called"
        List<Expense> expensesResult = expenseService.getExpensesToCurrentDate()

        then: "The amount is the expected"
        1 * expenseRepository.searchAllExpensesBetweenDates(startDate, endDate) >> expenses
        assert expensesResult.size() == 1
    }

    def "should return the total expended in the current month by category"() {
        given: "An stored expense"
        Expense expense = buildExpenseRequest()
        expense.date = LocalDate.now()
        List<Expense> expenses = [expense]
        LocalDate startDate = LocalDate.now().withDayOfMonth(1)
        LocalDate endDate = LocalDate.now().atTime(LocalTime.MAX).toLocalDate()
        String category = "FOOD"
        ExpenseCategory expenseCategory = ExpenseCategory.valueOf(category)

        when: "search all expenses is called"
        BigDecimal categoryAmount = expenseService.calculateExpensesToCurrentDateByCategory(category)

        then: "The amount is the expected"
        1 * expenseRepository.searchAllExpensesBetweenDatesByCategory(startDate, endDate, expenseCategory) >> expenses
        assert  categoryAmount == expense.amount
    }

    def buildExpenseRequest() {
        Expense expense = new Expense()
        expense.description = "Test Expense"
        expense.amount = 1000
        expense.date = LocalDate.of(2023, 1, 1)
        expense.category = "FOOD"
        return expense
    }

    def buildExpenseRequestWithParticipants() {
        Expense expense = buildExpenseRequest()
        expense.participants = [new Participant(name: "test", amount: BigDecimal.TEN),
                                new Participant(name: "test2", amount: BigDecimal.TEN)]

        return expense
    }

}
