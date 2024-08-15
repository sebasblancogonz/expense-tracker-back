package com.ducky.expensetracker.rest;

import com.ducky.expensetracker.model.Loan;
import com.ducky.expensetracker.request.LoanRequest;
import com.ducky.expensetracker.request.LoansRequest;
import com.ducky.expensetracker.response.AddLoanResponse;
import com.ducky.expensetracker.response.AddLoansResponse;
import com.ducky.expensetracker.response.ModifyLoanResponse;
import com.ducky.expensetracker.service.LoanService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(final LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddLoanResponse> addLoan(@RequestBody final LoanRequest loanRequest) {
        Loan loan = new Loan(loanRequest.getDescription(), loanRequest.getEntity(), loanRequest.getStartDate(), loanRequest.getFinishDate(), loanRequest.getInterest(), loanRequest.getTotalAmount());
        final String loanId = loanService.addLoan(loan);
        return ResponseEntity.ok(AddLoanResponse.builder().id(loanId).build());
    }

    @PostMapping(path = "/bulk", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddLoansResponse> addLoans(@RequestBody final LoansRequest loansRequest) {
        /*final List<String> loanIds = loanService.addLoans(loansRequest.getLoans());
        return ResponseEntity.ok(AddLoansResponse.builder().ids(loanIds).build());*/
        return null;
    }

    @PatchMapping(path = "/{loanId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ModifyLoanResponse> modifyLoan(@RequestBody final LoanRequest loanRequest,
                                                         @PathVariable final String loanId) {
        Loan loan = new Loan(loanRequest.getDescription(), loanRequest.getEntity(), loanRequest.getStartDate(), loanRequest.getFinishDate(), loanRequest.getInterest(), loanRequest.getTotalAmount());

        final Loan updatedLoan = loanService.updateLoan(loan,
                loanId);
        return ResponseEntity.ok(ModifyLoanResponse.builder().updatedLoan(updatedLoan).build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping(value = "/{loanId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Loan searchLoan(@PathVariable final String loanId) {
        return loanService.searchLoan(loanId);
    }

    @GetMapping(path = "/total/monthly", produces = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal getMonthlyTotal() {
        return loanService.getMonthlyTotal();
    }

    @DeleteMapping(value = "/{loanId}", produces = MediaType.APPLICATION_JSON_VALUE)
    void removeLoan(@PathVariable final String loanId) {
        loanService.removeLoan(loanId);
    }

}
