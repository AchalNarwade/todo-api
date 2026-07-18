package com.achal.todo_api.service.impl;

import com.achal.todo_api.dto.task.CreateTaskRequest;
import com.achal.todo_api.dto.task.TaskResponse;
import com.achal.todo_api.entity.Task;
import com.achal.todo_api.entity.TaskStatus;
import com.achal.todo_api.repository.TaskRepository;
import com.achal.todo_api.repository.UserRepository;
import com.achal.todo_api.service.TaskService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.achal.todo_api.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    //dependency injection
    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest createTaskRequest) {
        //Get current authentication
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        //Get principal
        Object principal = authentication.getPrincipal();

        //Convert to UserDetails
        UserDetails userDetails = (UserDetails) principal;

        //Get email
        String email = userDetails.getUsername();

        //Find User entity
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        //create task object
        Task task = Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .status(TaskStatus.PENDING)
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getCreatedAt()
        );
    }
}
