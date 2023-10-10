package com.ducky.expensetracker.rest;

import com.ducky.expensetracker.model.Installment;
import com.ducky.expensetracker.request.InstallmentRequest;
import com.ducky.expensetracker.request.InstallmentsRequest;
import com.ducky.expensetracker.response.AddInstallmentResponse;
import com.ducky.expensetracker.response.AddBulkInstallmentsResponse;
import com.ducky.expensetracker.response.ModifyInstallmentResponse;
import com.ducky.expensetracker.service.InstallmentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/installments")
public class InstallmentController {

    private final InstallmentService installmentService;

    public InstallmentController(final InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddInstallmentResponse> addInstallment(@RequestBody final InstallmentRequest installmentRequest) {
        final String installmentId = installmentService.addInstallment(installmentRequest.getInstallment());
        return ResponseEntity.ok(AddInstallmentResponse.builder().id(installmentId).build());
    }

    @PostMapping(path = "/bulk", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddBulkInstallmentsResponse> addInstallments(@RequestBody final InstallmentsRequest installmentsRequest) {
        final List<String> installmentIds = installmentService.addInstallments(installmentsRequest.getInstallments());
        return ResponseEntity.ok(AddBulkInstallmentsResponse.builder().ids(installmentIds).build());
    }

    @PatchMapping(path = "/{installmentId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ModifyInstallmentResponse> modifyInstallment(@RequestBody final InstallmentRequest installmentRequest,
                                                                @PathVariable final String installmentId) {
        final Installment updatedInstallment = installmentService.updateInstallment(installmentRequest.getInstallment(),
                installmentId);
        return ResponseEntity.ok(ModifyInstallmentResponse.builder().updatedInstallment(updatedInstallment).build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<Installment> getAllInstallments() {
        return installmentService.getAllInstallments();
    }

    @GetMapping(value = "/{installmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Installment searchInstallment(@PathVariable final String installmentId) {
        return installmentService.searchInstallment(installmentId);
    }

    @GetMapping(path = "/total/monthly", produces = MediaType.APPLICATION_JSON_VALUE)
    Double getMonthlyTotal() {
        return installmentService.getMonthlyTotal();
    }

}
