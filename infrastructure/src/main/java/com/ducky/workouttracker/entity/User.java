package com.ducky.workouttracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("users")
@NoArgsConstructor
public class User {

    @Id
    String id;

    private String username;

    public User(String id, String username) {
        super();
        this.id = id;
        this.username = username;
    }
}
