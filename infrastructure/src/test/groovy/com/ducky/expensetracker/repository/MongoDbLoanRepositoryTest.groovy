package com.ducky.expensetracker.repository

import com.ducky.expensetracker.mapper.LoansMapper
import com.ducky.expensetracker.model.Installment
import com.ducky.expensetracker.model.Loan
import spock.lang.Specification

import java.time.LocalDate

class MongoDbLoanRepositoryTest extends Specification {

    private MongoLoanRepository mongoLoanRepository
    private LoansMapper loansMapper

    private MongoDbLoanRepository mongoDbLoanRepository

    def setup() {
        mongoLoanRepository = Mock(MongoLoanRepository)
        loansMapper = Mock(LoansMapper)
        mongoDbLoanRepository = new MongoDbLoanRepository(mongoLoanRepository, loansMapper)
    }

    def "SearchLoan"() {
        given: "A loan id"
        String loanId = "1234567890"
        Loan expectedLoan = buildLoanRequest()
        com.ducky.expensetracker.entity.Loan loanEntity = buildLoanEntity(loanId)

        when: "searching for a loan"
        Loan loan = mongoDbLoanRepository.searchLoan(loanId)

        then: "the loan is returned"
        1 * mongoLoanRepository.findById(loanId) >> Optional.of(loanEntity)
        1 * loansMapper.toModel(loanEntity) >> expectedLoan

        and: "the loan has the expected values"
        assert loan.description == expectedLoan.description
        assert loan.monthlyAmount == expectedLoan.monthlyAmount
        assert loan.interest == expectedLoan.interest
        assert loan.startDate == expectedLoan.startDate
        assert loan.finishDate == expectedLoan.finishDate
        assert loan.totalRedeemed == expectedLoan.totalRedeemed
        assert loan.totalAmount == expectedLoan.totalAmount
        assert loan.installments == expectedLoan.installments
    }

    def "GetAllLoans"() {
        given: "A list of loans"
        List<Loan> expectedLoans = [buildLoanRequest(), buildLoanRequest()]
        List<com.ducky.expensetracker.entity.Loan> loanEntities = [buildLoanEntity(null), buildLoanEntity(null)]

        when: "getting all loans"
        List<Loan> loans = mongoDbLoanRepository.getAllLoans()

        then: "the loans are returned"
        1 * mongoLoanRepository.findAll() >> loanEntities
        1 * loansMapper.toModelList(loanEntities) >> expectedLoans
        assert loans.size() == 2
    }

    def "AddLoan"() {
        given: "A loan"
        Loan loanRequest = buildLoanRequest()
        com.ducky.expensetracker.entity.Loan loanEntity = buildLoanEntity(null)
        String expectedId = "1234567890"

        when: "adding a loan"
        String loanId = mongoDbLoanRepository.addLoan(loanRequest)

        then: "the loan is added"
        1 * loansMapper.toEntity(loanRequest) >> loanEntity
        1 * mongoLoanRepository.save(loanEntity) >> loanEntity
        assert loanId == expectedId
    }

    def "AddLoans"() {
        given: "A list of loans"
        List<Loan> loanRequests = [buildLoanRequest(), buildLoanRequest()]
        List<String> expectedIds = ["1234567890", "0987654321"]
        List<com.ducky.expensetracker.entity.Loan> loanEntities = [buildLoanEntity(expectedIds[0]),
                                                                   buildLoanEntity(expectedIds[1])]

        when: "adding a list of loans"
        List<String> loanIds = mongoDbLoanRepository.addLoans(loanRequests)

        then: "the loans are added"
        1 * loansMapper.toEntityList(loanRequests) >> loanEntities
        1 * mongoLoanRepository.saveAll(loanEntities) >> loanEntities
        assert loanIds == expectedIds
    }

    def "UpdateLoan"() {
        given: "A loan"
        Loan loanRequest = buildLoanRequest()
        com.ducky.expensetracker.entity.Loan loanEntity = buildLoanEntity("1234567890")
        String expectedId = "1234567890"

        when: "updating a loan"
        Loan updatedLoan = mongoDbLoanRepository.updateLoan(loanRequest, expectedId)

        then: "the loan is updated"
        1 * mongoLoanRepository.findById(expectedId) >> Optional.of(loanEntity)
        1 * loansMapper.toEntity(loanRequest) >> loanEntity
        1 * mongoLoanRepository.save(loanEntity) >> loanEntity
        1 * loansMapper.toModel(loanEntity) >> loanRequest
        assert updatedLoan.getDescription() == loanRequest.getDescription()
        assert updatedLoan.getMonthlyAmount() == loanRequest.getMonthlyAmount()
        assert updatedLoan.getInterest() == loanRequest.getInterest()
        assert updatedLoan.getStartDate() == loanRequest.getStartDate()
        assert updatedLoan.getFinishDate() == loanRequest.getFinishDate()
        assert updatedLoan.getTotalRedeemed() == loanRequest.getTotalRedeemed()
        assert updatedLoan.getTotalAmount() == loanRequest.getTotalAmount()
        assert updatedLoan.getInstallments() == loanRequest.getInstallments()
    }

    def "DeleteLoan"() {
        given: "A loan id"
        String loanId = "1234567890"

        when: "deleting a loan"
        mongoDbLoanRepository.removeLoan(loanId)

        then: "the loan is deleted"
        1 * mongoLoanRepository.deleteById(loanId)
    }

    def buildLoanRequest() {
        Loan loanModel = new Loan()
        loanModel.setId("1234567890")
        loanModel.setDescription("Loan description")
        loanModel.setMonthlyAmount(1000.0)
        loanModel.setInterest(0.1)
        loanModel.setStartDate(LocalDate.of(2021, 1, 1))
        loanModel.setFinishDate(LocalDate.of(2021, 12, 31))
        loanModel.setTotalRedeemed(0.0)
        loanModel.setRemainingInstallments(12)
        loanModel.setInterestTotalAmount(100.0)
        loanModel.setTotalAmount(1000.0)
        loanModel.setInstallments(buildInstallments())
        return loanModel
    }

    def buildInstallments() {
        Installment installmentModel = new Installment()
        installmentModel.setAmount(1000.0)
        installmentModel.setInterest(0.1)
        installmentModel.setRedeemed(0.0)
        installmentModel.setTotalRedeemed(0.0)
        installmentModel.setRemainingInstallments(12)
        installmentModel.setRemainingAmount(1000.0)
        installmentModel.setDate(LocalDate.of(2021, 1, 1))
        return [installmentModel]
    }

    def buildLoanEntity(String id) {
        com.ducky.expensetracker.entity.Loan loanEntity = new com.ducky.expensetracker.entity.Loan()
        loanEntity.setId(id != null ? id : "1234567890")
        loanEntity.setDescription("Loan description")
        loanEntity.setMonthlyAmount(1000.0)
        loanEntity.setInterest(0.1)
        loanEntity.setStartDate(LocalDate.of(2021, 1, 1))
        loanEntity.setFinishDate(LocalDate.of(2021, 12, 31))
        loanEntity.setTotalRedeemed(0.0)
        loanEntity.setTotalAmount(1000.0)
        loanEntity.setInterestTotalAmount(100.0)
        loanEntity.setRemainingInstallments(12)
        loanEntity.setInstallments(buildInstallmentsEntity())
        return loanEntity
    }

    def buildInstallmentsEntity() {
        com.ducky.expensetracker.entity.Installment installmentEntity = new com.ducky.expensetracker.entity.Installment()
        installmentEntity.setAmount(1000.0)
        installmentEntity.setInterest(0.1)
        installmentEntity.setRedeemed(0.0)
        installmentEntity.setTotalRedeemed(0.0)
        installmentEntity.setRemainingInstallments(12)
        installmentEntity.setRemainingAmount(1000.0)
        installmentEntity.setDate(LocalDate.of(2021, 1, 1))
        return [installmentEntity]
    }

}
