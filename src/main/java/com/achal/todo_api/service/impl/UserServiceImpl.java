package com.achal.todo_api.service.impl;

import com.achal.todo_api.dto.auth.LoginRequest;
import com.achal.todo_api.dto.auth.LoginResponse;
import com.achal.todo_api.dto.auth.RegisterRequest;
import com.achal.todo_api.dto.auth.RegisterResponse;
import com.achal.todo_api.entity.User;
import com.achal.todo_api.exception.InvalidPasswordException;
import com.achal.todo_api.exception.UserAlreadyExistsException;
import com.achal.todo_api.exception.UserNotFoundException;
import com.achal.todo_api.repository.UserRepository;
import com.achal.todo_api.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    //dependency injection
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new RegisterResponse("User registered successfully");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new UserNotFoundException("User Not Found"));
        if(!passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()
        )){
            throw new InvalidPasswordException("Invalid password");
        }
        return new LoginResponse("Login successful");
    }
}
