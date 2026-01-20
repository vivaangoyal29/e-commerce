package com.example.e_commerce.repository;

import com.example.e_commerce.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository {
    Optional<User> findByEmail(String email);
}
