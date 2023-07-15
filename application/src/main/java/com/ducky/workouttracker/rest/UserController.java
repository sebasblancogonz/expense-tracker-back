package com.ducky.workouttracker.rest;

import com.ducky.workouttracker.model.User;
import com.ducky.workouttracker.request.UserRequest;
import com.ducky.workouttracker.response.CreateUserResponse;
import com.ducky.workouttracker.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CreateUserResponse createUser(@RequestBody final UserRequest userRequest) {
        final String userId = userService.createUser(userRequest.getUser());
       return CreateUserResponse.builder().id(userId).build();
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    User searchUser(@PathVariable final String userId) {
        return userService.searchUser(userId);
    }
}
