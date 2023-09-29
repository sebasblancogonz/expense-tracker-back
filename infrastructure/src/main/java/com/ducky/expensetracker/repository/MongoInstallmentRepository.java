package com.ducky.expensetracker.repository;

import com.ducky.expensetracker.entity.Installment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoInstallmentRepository extends MongoRepository<Installment, String> {

    Installment findByDescription(String description);

}
