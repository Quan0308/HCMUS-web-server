package com.project.hcmuswebserver.repositories;

import com.project.hcmuswebserver.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
