package com.achal.todo_api.controller;

import com.achal.todo_api.dto.auth.LoginRequest;
import com.achal.todo_api.dto.auth.LoginResponse;
import com.achal.todo_api.dto.auth.RegisterRequest;
import com.achal.todo_api.dto.auth.RegisterResponse;
import com.achal.todo_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication",description = "User Registration and login APIs")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Register a new user",
            description = "Create a new user account."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "User registered successfully"),
            @ApiResponse(responseCode = "400",description = "Validation default"),
            @ApiResponse(responseCode = "409",description = "User already exists")
    })
    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request){
        return userService.register(request);
    }

    @Operation(
            summary = "Login user",
            description = "Authenticates a user and returns a JWT token"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Login successful"),
            @ApiResponse(responseCode = "401",description = "Invalid email or password")
    })
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
}
