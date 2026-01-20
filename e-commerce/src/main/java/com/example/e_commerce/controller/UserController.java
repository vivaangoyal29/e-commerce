package com.example.e_commerce.controller;

import com.example.e_commerce.dto.CreateUserRequest;
import com.example.e_commerce.dto.UserResponse;
import com.example.e_commerce.model.User;
import com.example.e_commerce.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);
        return toResponse(user);
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable String userId) {
        return toResponse(userService.getById(userId));
    }

    private UserResponse toResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        res.setEmail(user.getEmail());
        return res;
    }
}
