package com.project.hcmuswebserver.entities;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String email;
    private String password;

    @CreatedDate
    private OffsetDateTime createdAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {}
}
