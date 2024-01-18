package com.ducky.expensetracker.repository

import com.ducky.expensetracker.entity.Expense
import com.ducky.expensetracker.entity.Participant
import com.ducky.expensetracker.mapper.ExpensesMapper
import spock.lang.Specification

import java.time.LocalDate

class MongoDbExpenseRepositoryTest extends Specification {

    private MongoExpenseRepository mongoExpenseRepository
    private ExpensesMapper expensesMapper
    private MongoDbExpenseRepository mongoDbExpenseRepository

    def setup() {
        mongoExpenseRepository = Mock(MongoExpenseRepository)
        expensesMapper = Mock(ExpensesMapper)
        mongoDbExpenseRepository = new MongoDbExpenseRepository(mongoExpenseRepository, expensesMapper)
    }

    def "SearchExpense"() {
        given: "An expense id"
        String expenseId = "1234567890"
        com.ducky.expensetracker.model.Expense expectedExpense = buildExpenseRequest()
        Expense expenseEntity = buildExpenseEntity(expenseId)

        when: "searching for an expense"
        com.ducky.expensetracker.model.Expense expense = mongoDbExpenseRepository.searchExpense(expenseId)

        then: "the expense is returned"
        1 * mongoExpenseRepository.findById(expenseId) >> Optional.of(expenseEntity)
        1 * expensesMapper.toModel(expenseEntity) >> expectedExpense

        and: "the expense has the expected values"
        assert expense.description == expectedExpense.description
        assert expense.amount == expectedExpense.amount
        assert expense.date == expectedExpense.date
        assert expense.participants == expectedExpense.participants
    }

    def "AddExpense"() {
        given:
        String expectedId = "1234567890"
        com.ducky.expensetracker.model.Expense expenseRequest = buildExpenseRequest()
        com.ducky.expensetracker.model.Expense expenseWithRemainingData = expenseRequest
        Expense expenseEntity = buildExpenseEntity(expectedId)

        when:
        String expenseId = mongoDbExpenseRepository.addExpense(expenseRequest)

        then:
        1 * mongoExpenseRepository.save(expenseEntity) >> expenseEntity
        1 * expensesMapper.toEntity(expenseWithRemainingData) >> expenseEntity
        assert expenseId == expectedId
    }

    def "ModifyExpense"() {
        given:
        com.ducky.expensetracker.model.Expense expenseRequest = buildExpenseRequest()

        when:
        String expenseId = mongoDbExpenseRepository.modifyExpense(expenseRequest)

        then:
        assert expenseId == null
    }

    def buildExpenseRequest() {
        com.ducky.expensetracker.model.Expense expense = new com.ducky.expensetracker.model.Expense()
        expense.description = "Test Expense"
        expense.amount = 1000
        expense.date = LocalDate.of(2023, 1, 1)
        expense.participants = [new com.ducky.expensetracker.model.Participant(name: "test", amount: BigDecimal.TEN),
                                new com.ducky.expensetracker.model.Participant(name: "test2", amount: BigDecimal.TEN)]
        return expense
    }

    def buildExpenseEntity(String expenseId) {
        Expense expenseEntity = new Expense()
        expenseEntity.id = expenseId
        expenseEntity.description = "Test Expense"
        expenseEntity.amount = 1000
        expenseEntity.date = LocalDate.of(2023, 1, 1)
        expenseEntity.participants = [new Participant(name: "test", amount: BigDecimal.TEN),
                                      new Participant(name: "test2", amount: BigDecimal.TEN)]
        return expenseEntity
    }

}
