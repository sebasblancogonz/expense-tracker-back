package com.ducky.expensetracker.service.impl

import com.ducky.expensetracker.model.Installment
import com.ducky.expensetracker.model.Loan
import com.ducky.expensetracker.repository.LoanRepository
import spock.lang.Specification

import java.time.LocalDate

class LoanServiceImplTest extends Specification {

    private LoanRepository loanRepository

    private LoanServiceImpl loanService

    def setup() {
        loanRepository = Mock(LoanRepository)
        loanService = new LoanServiceImpl(loanRepository)
    }

    def "AddLoan"() {
        given:
        Loan loanRequest = buildLoanRequest()
        Loan loanWithRemainingData = loanRequest
        addRemainingDataToLoan(loanWithRemainingData)
        String expectedId = "1234567890"

        when:
        String loanId = loanService.addLoan(loanRequest)

        then:
        1 * loanRepository.addLoan(loanWithRemainingData) >> expectedId
        loanId != null

    }

    def "AddLoans"() {
        given:
        List<Loan> loanRequests = [buildLoanRequest(), buildLoanRequest()]
        List<Loan> loansWithRemainingData = loanRequests
        loansWithRemainingData.each { loan ->
            addRemainingDataToLoan(loan)
        }
        List<String> expectedIds = ["1234567890", "0987654321"]

        when:
        List<String> loanIds = loanService.addLoans(loanRequests)

        then:
        1 * loanRepository.addLoans(loansWithRemainingData) >> expectedIds
        assert loanIds.size() == 2
    }

    def "SearchLoan"() {
        given: "A loan id"
        String loanId = "1234567890"
        Loan expectedLoan = buildLoanRequest()
        addRemainingDataToLoan(expectedLoan)

        when: "SearchLoan is called"
        Loan loan = loanService.searchLoan(loanId)

        then: "LoanRepository.searchLoan is called and returns the loan"
        1 * loanRepository.searchLoan(loanId) >> expectedLoan
        assert loan != null
    }

    def "GetAllLoans"() {
        given: "A list of loans"
        List<Loan> expectedLoans = [buildLoanRequest(), buildLoanRequest()]
        expectedLoans.each { loan ->
            addRemainingDataToLoan(loan)
        }

        when: "GetAllLoans is called"
        List<Loan> loans = loanService.getAllLoans()

        then: "LoanRepository.getAllLoans is called and returns the loans"
        1 * loanRepository.getAllLoans() >> expectedLoans
        assert loans.size() == 2
    }

    def "GetMonthlyTotal"() {
        given: "A loan id"
        BigDecimal expectedMonthlyTotal = 1000.00
        List<Loan> storedLoans = [buildLoanRequest(), buildLoanRequest()]
        storedLoans.each { loan ->
            addRemainingDataToLoan(loan)
        }

        when: "GetMonthlyTotal is called"
        BigDecimal monthlyTotal = loanService.getMonthlyTotal()

        then: "LoanRepository.getMonthlyTotal is called and returns the monthly total"
        1 * loanRepository.getAllLoans() >> storedLoans
        assert monthlyTotal == expectedMonthlyTotal
    }

    def "UpdateLoan"() {
        given: "A loan id and a loan"
        String loanId = "1234567890"
        Loan loanRequest = buildLoanRequest()
        Loan loanWithRemainingData = loanRequest
        addRemainingDataToLoan(loanWithRemainingData)

        when: "UpdateLoan is called"
        Loan updatedLoan = loanService.updateLoan(loanRequest, loanId)

        then: "LoanRepository.updateLoan is called"
        1 * loanRepository.updateLoan(loanWithRemainingData, loanId) >> loanWithRemainingData
        assert updatedLoan == loanWithRemainingData

    }

    def buildLoanRequest() {
        Loan loan = new Loan()
        loan.setTotalAmount(1000.00)
        loan.setInterest(1.0)
        loan.setMonthlyAmount(500.00)
        loan.setStartDate(LocalDate.of(2020, 1, 1))
        loan.setFinishDate(LocalDate.of(2020, 3, 31))
        loan.setDescription("TestLoan")
        return loan
    }

    void addRemainingDataToLoan(Loan loan) {
        Installment expectedInstallment1 = buildInstallment(500.00, 1.0, 500.00, 500.00, 500.00, LocalDate.of(2020, 2, 1))
        Installment expectedInstallment2 = buildInstallment(500.00, 1.0, 500.00, 500.00, 500.00, LocalDate.of(2020, 3, 1))
        loan.setInstallments([expectedInstallment1, expectedInstallment2])
    }

    def buildInstallment(BigDecimal amount, BigDecimal interest, BigDecimal redeemed,
                         BigDecimal remainingAmount, BigDecimal totalRedeemed, LocalDate date) {
        Installment installment = new Installment()
        installment.setAmount(amount)
        installment.setInterest(interest)
        installment.setRedeemed(redeemed)
        installment.setRemainingAmount(remainingAmount)
        installment.setTotalRedeemed(totalRedeemed)
        installment.setDate(date)
        return installment
    }
}
