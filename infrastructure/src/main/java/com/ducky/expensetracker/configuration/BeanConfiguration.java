package com.ducky.expensetracker.configuration;

import com.ducky.expensetracker.ExpenseTrackerApplication;
import com.ducky.expensetracker.repository.ExpenseRepository;
import com.ducky.expensetracker.repository.InstallmentRepository;
import com.ducky.expensetracker.service.ExpenseService;
import com.ducky.expensetracker.service.InstallmentService;
import com.ducky.expensetracker.service.impl.ExpenseServiceImpl;
import com.ducky.expensetracker.service.impl.InstallmentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ExpenseTrackerApplication.class)
public class BeanConfiguration {

    @Bean
    ExpenseService userService(final ExpenseRepository expenseRepository) {
        return new ExpenseServiceImpl(expenseRepository);
    }

    @Bean
    InstallmentService installmentService(final InstallmentRepository installmentRepository) {
        return new InstallmentServiceImpl(installmentRepository);
    }

}
