package com.achal.todo_api.service;

import com.achal.todo_api.dto.task.CreateTaskRequest;
import com.achal.todo_api.dto.task.TaskResponse;
import com.achal.todo_api.dto.task.UpdateTaskRequest;
import org.hibernate.sql.Update;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest createTaskRequest);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Long id);

    TaskResponse updateTask(Long id, UpdateTaskRequest updateTaskRequest);

    void deleteTask(Long id);
}
