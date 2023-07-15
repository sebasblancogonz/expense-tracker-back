package com.ducky.workouttracker.configuration;

import com.ducky.workouttracker.WorkoutTrackerApplication;
import com.ducky.workouttracker.repository.UserRepository;
import com.ducky.workouttracker.service.UserService;
import com.ducky.workouttracker.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = WorkoutTrackerApplication.class)
public class BeanConfiguration {

    @Bean
    UserService userService(final UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

}
