package com.ducky.expensetracker.repository

import com.ducky.expensetracker.mapper.ExpensesMapper
import com.ducky.expensetracker.model.Expense
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
        Expense expectedExpense = buildExpenseRequest()
        com.ducky.expensetracker.entity.Expense expenseEntity = buildExpenseEntity(expenseId)

        when: "searching for an expense"
        Expense expense = mongoDbExpenseRepository.searchExpense(expenseId)

        then: "the expense is returned"
        1 * mongoExpenseRepository.findById(expenseId) >> Optional.of(expenseEntity)
        1 * expensesMapper.toModel(expenseEntity) >> expectedExpense

        and: "the expense has the expected values"
        assert expense.description == expectedExpense.description
        assert expense.amount == expectedExpense.amount
        assert expense.paymentDate == expectedExpense.paymentDate
    }

    def "AddExpense"() {
        given:
        String expectedId = "1234567890"
        Expense expenseRequest = buildExpenseRequest()
        Expense expenseWithRemainingData = expenseRequest
        com.ducky.expensetracker.entity.Expense expenseEntity = buildExpenseEntity(expectedId)

        when:
        String expenseId = mongoDbExpenseRepository.addExpense(expenseRequest)

        then:
        1 * mongoExpenseRepository.save(expenseEntity) >> expenseEntity
        1 * expensesMapper.toEntity(expenseWithRemainingData) >> expenseEntity
        assert expenseId == expectedId
    }

    def "ModifyExpense"() {
        given:
        Expense expenseRequest = buildExpenseRequest()

        when:
        String expenseId = mongoDbExpenseRepository.modifyExpense(expenseRequest)

        then:
        assert expenseId == null
    }

    def buildExpenseRequest() {
        Expense expense = new Expense()
        expense.description = "Test Expense"
        expense.amount = 1000
        expense.paymentDate = LocalDate.of(2023, 1, 1)
        return expense
    }

    def buildExpenseEntity(String expenseId) {
        com.ducky.expensetracker.entity.Expense expenseEntity = new com.ducky.expensetracker.entity.Expense()
        expenseEntity.id = expenseId
        expenseEntity.description = "Test Expense"
        expenseEntity.amount = 1000
        expenseEntity.paymentDate = LocalDate.of(2023, 1, 1)
        return expenseEntity
    }

}
