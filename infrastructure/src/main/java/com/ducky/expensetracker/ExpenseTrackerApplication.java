package com.ducky.expensetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApplication {

    public static void main(final String[] args) {
        SpringApplication application = new SpringApplication(ExpenseTrackerApplication.class);
        application.run(args);
    }

}
