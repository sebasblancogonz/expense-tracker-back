package com.ducky.workouttracker.service.impl;

import com.ducky.workouttracker.model.User;
import com.ducky.workouttracker.repository.UserRepository;
import com.ducky.workouttracker.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(User user) {
        return userRepository.saveUser(user);
    }

    @Override
    public User searchUser(String userId) {
        return userRepository.searchUser(userId);
    }
}
