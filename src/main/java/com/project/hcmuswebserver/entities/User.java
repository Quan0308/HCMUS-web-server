package com.project.hcmuswebserver.entities;

import com.project.hcmuswebserver.models.LoginResponse;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Document(collection = "users")
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String email;
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean verifyPassword(String password, BCryptPasswordEncoder encoder) {
        return encoder.matches(password, this.password);
    }

    public LoginResponse toLoginResponse() {
        return new LoginResponse(this.email + this.createdAt.toString(), "refreshToken");
    }
}
