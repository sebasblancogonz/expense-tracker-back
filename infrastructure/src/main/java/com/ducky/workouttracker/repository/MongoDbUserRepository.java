package com.ducky.workouttracker.repository;


import com.ducky.workouttracker.mapper.UserMapper;
import com.ducky.workouttracker.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Primary
public class MongoDbUserRepository implements UserRepository {

    private final SpringDataMongoUserRepository repository;
    private final UserMapper userMapper;

    public MongoDbUserRepository(final SpringDataMongoUserRepository repository, final UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public User searchUser(String userId) {
        Optional<com.ducky.workouttracker.entity.User> userEntity = repository.findById(userId);
        return userEntity.map(userMapper::toModel).orElse(null);
    }

    @Override
    public String saveUser(User user) {
        com.ducky.workouttracker.entity.User userEntity = userMapper.toEntity(user);
        return repository.save(userEntity).getId();
    }

    @Override
    public User modifyUser(User user) {
        return null;
    }
}
