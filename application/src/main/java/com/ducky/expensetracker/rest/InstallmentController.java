package com.ducky.expensetracker.rest;

import com.ducky.expensetracker.model.Installment;
import com.ducky.expensetracker.request.InstallmentRequest;
import com.ducky.expensetracker.response.AddInstallmentResponse;
import com.ducky.expensetracker.service.InstallmentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/installments")
public class InstallmentController {

    private final InstallmentService installmentService;

    public InstallmentController(final InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddInstallmentResponse> addExpense(@RequestBody final InstallmentRequest installmentRequest) {
        final String installmentId = installmentService.addInstallment(installmentRequest.getInstallment());
        return ResponseEntity.ok(AddInstallmentResponse.builder().id(installmentId).build());
    }

    @GetMapping(value = "/{installmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Installment searchInstallment(@PathVariable final String installmentId) {
        return installmentService.searchInstallment(installmentId);
    }

}
