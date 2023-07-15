package com.ducky.workouttracker.request;

import com.ducky.workouttracker.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NonNull
    private User user;

}
