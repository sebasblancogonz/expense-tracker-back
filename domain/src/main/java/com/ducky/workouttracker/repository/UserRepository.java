package com.ducky.workouttracker.repository;

import com.ducky.workouttracker.model.User;

public interface UserRepository {

    User searchUser(String userId);

    String saveUser(User user);

    User modifyUser(User user);

}
