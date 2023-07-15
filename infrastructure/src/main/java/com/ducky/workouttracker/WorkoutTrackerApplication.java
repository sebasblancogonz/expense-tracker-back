package com.ducky.workouttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorkoutTrackerApplication {

    public static void main(final String[] args) {
        SpringApplication application = new SpringApplication(WorkoutTrackerApplication.class);
        application.run(args);
    }
}
