package com.example.e_commerce.service;

import com.example.e_commerce.dto.CreateUserRequest;
import com.example.e_commerce.model.User;
import com.example.e_commerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest request) {

        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already exists");
                });

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        return (User) userRepository.save(user);
    }

    public User getById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
