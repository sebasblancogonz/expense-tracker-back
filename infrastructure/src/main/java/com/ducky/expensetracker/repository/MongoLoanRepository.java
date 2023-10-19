package com.ducky.expensetracker.repository;

import com.ducky.expensetracker.entity.Installment;
import com.ducky.expensetracker.entity.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoLoanRepository extends MongoRepository<Loan, String> {

    Loan findByDescription(String description);

}
