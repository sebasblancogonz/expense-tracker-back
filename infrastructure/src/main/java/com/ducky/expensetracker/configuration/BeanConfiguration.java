package com.ducky.expensetracker.configuration;

import com.ducky.expensetracker.WorkoutTrackerApplication;
import com.ducky.expensetracker.repository.ExpenseRepository;
import com.ducky.expensetracker.service.ExpenseService;
import com.ducky.expensetracker.service.impl.ExpenseServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = WorkoutTrackerApplication.class)
public class BeanConfiguration {

    @Bean
    ExpenseService userService(final ExpenseRepository expenseRepository) {
        return new ExpenseServiceImpl(expenseRepository);
    }

}
