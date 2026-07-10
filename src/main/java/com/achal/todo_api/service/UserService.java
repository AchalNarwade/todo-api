package com.achal.todo_api.service;

import com.achal.todo_api.dto.auth.LoginRequest;
import com.achal.todo_api.dto.auth.LoginResponse;
import com.achal.todo_api.dto.auth.RegisterRequest;
import com.achal.todo_api.dto.auth.RegisterResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest loginRequest);
}
