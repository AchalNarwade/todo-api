package com.achal.todo_api.service.impl;

import com.achal.todo_api.dto.task.CreateTaskRequest;
import com.achal.todo_api.dto.task.TaskResponse;
import com.achal.todo_api.dto.task.UpdateTaskRequest;
import com.achal.todo_api.entity.Task;
import com.achal.todo_api.entity.TaskStatus;
import com.achal.todo_api.exception.TaskNotFoundException;
import com.achal.todo_api.repository.TaskRepository;
import com.achal.todo_api.repository.UserRepository;
import com.achal.todo_api.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.achal.todo_api.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    //dependency injection
    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    //helper method
    private User getCurrentUser() {
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
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest createTaskRequest) {

        User user = getCurrentUser();

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

    @Override
    public List<TaskResponse> getAllTasks() {
        User user = getCurrentUser();
        List<Task>tasks = taskRepository.findByUser(user);

        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task:tasks){
            TaskResponse taskResponse = new TaskResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getCreatedAt()
            );
            taskResponses.add(taskResponse);
        }
        return taskResponses;
    }

    public TaskResponse getTaskById(Long id){
        User user = getCurrentUser();
        Task task = taskRepository
                .findByIdAndUser(id,user)
                .orElseThrow(()-> new TaskNotFoundException("Task not found"));
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt()
        );
    }

    @Override
    public TaskResponse updateTask(Long id, UpdateTaskRequest updateTaskRequest) {
        User user = getCurrentUser();

        Task task = taskRepository.findByIdAndUser(id,user)
                .orElseThrow(()-> new RuntimeException("Task not found"));
        task.setTitle(updateTaskRequest.getTitle());
        task.setDescription(updateTaskRequest.getDescription());
        task.setStatus(updateTaskRequest.getStatus());

        Task updatedTask = taskRepository.save(task);

        return new TaskResponse(
                updatedTask.getId(),
                updatedTask.getTitle(),
                updatedTask.getDescription(),
                updatedTask.getStatus(),
                updatedTask.getCreatedAt()
        );
    }

    @Override
    public void deleteTask(Long id) {
        User user = getCurrentUser();

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskRepository.delete(task);
    }


}
