package com.achal.todo_api.service.impl;

import com.achal.todo_api.repository.UserRepository;
import com.achal.todo_api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
