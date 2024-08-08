package com.ducky.expensetracker.mapper

import com.ducky.expensetracker.model.Expense
import spock.lang.Specification

import java.time.LocalDate

class ExpensesMapperTest extends Specification {

    private ExpensesMapper expensesMapper

    def setup() {
        expensesMapper = new ExpensesMapperImpl()
    }

    def "ToEntity"() {
        given:
        def expenses = buildExpenseModel()

        when:
        def result = expensesMapper.toEntity(expenses)

        then:
        assert result.amount == expenses.amount
        assert result.description == expenses.description
        assert result.paymentDate == expenses.paymentDate
    }

    def "ToModel"() {
        given:
        def expenses = buildExpenseEntity()

        when:
        def result = expensesMapper.toModel(expenses)

        then:
        assert result.amount == expenses.amount
        assert result.description == expenses.description
        assert result.date == expenses.date
    }

    def "ToModelList"() {
        given:
        def expenses = [buildExpenseEntity()]

        when:
        def result = expensesMapper.toModelList(expenses)

        then:
        assert result.size() == 1
        assert result[0].amount == expenses[0].amount
        assert result[0].description == expenses[0].description
        assert result[0].date == expenses[0].date
    }

    def buildExpenseModel() {
        Expense expense = new Expense()
        expense.amount = 100
        expense.description = "Test"
        expense.date = LocalDate.of(2020, 1, 1)
        return expense
    }

    def buildExpenseEntity() {
        com.ducky.expensetracker.entity.Expense expense = new com.ducky.expensetracker.entity.Expense()
        expense.id = 1
        expense.amount = 100
        expense.description = "Test"
        expense.date = LocalDate.of(2020, 1, 1)
        return expense
    }

}
