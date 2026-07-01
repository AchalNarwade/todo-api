package com.achal.todo_api.service.impl;

import com.achal.todo_api.dto.auth.RegisterRequest;
import com.achal.todo_api.dto.auth.RegisterResponse;
import com.achal.todo_api.entity.User;
import com.achal.todo_api.repository.UserRepository;
import com.achal.todo_api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

        return new RegisterResponse("User registered successfully");
    }
}
