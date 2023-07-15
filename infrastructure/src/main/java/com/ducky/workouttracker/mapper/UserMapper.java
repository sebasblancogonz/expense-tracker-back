package com.ducky.workouttracker.mapper;

import com.ducky.workouttracker.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toEntity(com.ducky.workouttracker.model.User userModel);

    com.ducky.workouttracker.model.User toModel(User userEntity);


}
