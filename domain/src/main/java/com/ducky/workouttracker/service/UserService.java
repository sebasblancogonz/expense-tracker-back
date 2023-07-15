package com.ducky.workouttracker.service;

import com.ducky.workouttracker.model.User;

public interface UserService {

    String createUser(User user);

    User searchUser(String userId);

}
