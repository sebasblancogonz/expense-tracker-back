package com.ducky.expensetracker.service.impl;

import com.ducky.expensetracker.model.Installment;
import com.ducky.expensetracker.model.Loan;
import com.ducky.expensetracker.repository.LoanRepository;
import com.ducky.expensetracker.service.LoanService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public final class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public String addLoan(Loan loan) {
        calculateRemainingData(loan);
        return loanRepository.addLoan(loan);
    }

    @Override
    public List<String> addLoans(List<Loan> loans) {
        loans.forEach(this::calculateRemainingData);
        return loanRepository.addLoans(loans);
    }

    @Override
    public Loan searchLoan(String loanId) {
        return loanRepository.searchLoan(loanId);
    }

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.getAllLoans();
    }

    @Override
    public BigDecimal getMonthlyTotal() {
        return scaleTo2Decimals(calculateMonthlyTotal());
    }

    @Override
    public Loan updateLoan(Loan loan, String loanId) {
        calculateRemainingData(loan);
        return loanRepository.updateLoan(loan, loanId);
    }

    private BigDecimal calculateMonthlyTotal() {
        return scaleTo2Decimals(loanRepository.getAllLoans()
                .stream()
                .map(Loan::getMonthlyAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    private void calculateRemainingData(Loan loan) {
        LocalDate finishDate = loan.getFinishDate();
        BigDecimal monthlyInterest = calculateMonthlyInterest(loan);
        BigDecimal monthlyAmount = calculateMonthlyAmount(loan, monthlyInterest);
        int remainingInstallments = calculateRemainingInstallments(LocalDate.now(), finishDate);
        loan.setMonthlyAmount(monthlyAmount);
        loan.setRemainingInstallments(remainingInstallments);

        List<Installment> installments = calculateInstallments(loan);
        loan.setInstallments(installments);
    }

    private List<Installment> calculateInstallments(Loan loan) {
        LocalDate startDate = loan.getStartDate();
        LocalDate finishDate = loan.getFinishDate();
        BigDecimal monthlyInterest = calculateMonthlyInterest(loan);
        BigDecimal monthlyAmount = loan.getMonthlyAmount();
        BigDecimal interestTotalAmount = BigDecimal.ZERO;
        BigDecimal totalRedeemed = BigDecimal.ZERO;

        List<Installment> installments = new ArrayList<>();
        for (LocalDate date = startDate.plusMonths(1); date.isBefore(finishDate); date = date.plusMonths(1)) {
            BigDecimal interestAmount;
            BigDecimal totalRedeemedAmount;
            BigDecimal redeemedAmount;
            if (installments.isEmpty()) {
                interestAmount = calculateInstallmentInterestAmount(loan.getTotalAmount(), monthlyInterest);
                redeemedAmount = calculateRedeemedAmount(monthlyAmount, interestAmount);
                totalRedeemedAmount = getAmountPaid(redeemedAmount, BigDecimal.ZERO);
            } else {
                interestAmount = calculateInstallmentInterestAmount(installments.get(installments.size() - 1).getRemainingAmount(), monthlyInterest);
                redeemedAmount = calculateRedeemedAmount(loan.getMonthlyAmount(), interestAmount);
                totalRedeemedAmount = getAmountPaid(redeemedAmount, installments.get(installments.size() - 1).getTotalRedeemed());
            }

            BigDecimal remainingAmount = scaleTo2Decimals(loan.getTotalAmount().subtract(totalRedeemedAmount));
            int remainingInstallments = calculateRemainingInstallments(date, finishDate);
            Installment installment = new Installment();
            installment.setDate(date);
            installment.setAmount(scaleTo2Decimals(monthlyAmount));
            installment.setInterest(scaleTo2Decimals(interestAmount));
            installment.setRedeemed(scaleTo2Decimals(redeemedAmount));
            installment.setTotalRedeemed(scaleTo2Decimals(totalRedeemedAmount));
            installment.setRemainingInstallments(remainingInstallments);
            installment.setRemainingAmount(scaleTo2Decimals(remainingAmount));
            installments.add(installment);
            if (date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())) {
                totalRedeemed = totalRedeemed.add(redeemedAmount);
            }
            interestTotalAmount = interestTotalAmount.add(interestAmount);
        }
        loan.setInterestTotalAmount(scaleTo2Decimals(interestTotalAmount));
        loan.setTotalRedeemed(scaleTo2Decimals(totalRedeemed));
        return installments;
    }

    private BigDecimal calculateRedeemedAmount(BigDecimal amount, BigDecimal interestAmount) {
        return amount.subtract(interestAmount);
    }

    private BigDecimal calculateInstallmentInterestAmount(BigDecimal amount, BigDecimal monthlyInterest) {
        return amount.multiply(monthlyInterest);
    }

    private BigDecimal calculateMonthlyAmount(Loan loan, BigDecimal monthlyInterest) {
        int totalInstallments = getMonthsBetween(loan.getStartDate(), loan.getFinishDate());
        BigDecimal onePlusMonthlyInterest = BigDecimal.ONE.add(monthlyInterest);

        BigDecimal powOnePlusMonthlyInterest = onePlusMonthlyInterest.pow(totalInstallments);

        BigDecimal denominator = powOnePlusMonthlyInterest.subtract(BigDecimal.ONE);

        BigDecimal numerator = loan.getTotalAmount()
                .multiply(monthlyInterest)
                .multiply(powOnePlusMonthlyInterest);

        return numerator.divide(denominator, MathContext.DECIMAL128);

    }

    private BigDecimal calculateMonthlyInterest(Loan loan) {
        return loan.getInterest().divide(BigDecimal.valueOf(1200), MathContext.DECIMAL128);
    }

    private int calculateRemainingInstallments(LocalDate date, LocalDate finishDate) {
        return getMonthsBetween(date, finishDate);
    }

    private BigDecimal getAmountPaid(BigDecimal loanAmount, BigDecimal totalRedeemed) {
        return loanAmount.add(totalRedeemed);
    }

    private static int getMonthsBetween(LocalDate start, LocalDate end) {
        return (int) ChronoUnit.MONTHS.between(YearMonth.from(start), YearMonth.from(end));
    }

    private BigDecimal scaleTo2Decimals(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }

}
