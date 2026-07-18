package com.achal.todo_api.service;

import com.achal.todo_api.dto.task.CreateTaskRequest;
import com.achal.todo_api.dto.task.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest createTaskRequest);

    List<TaskResponse> getAllTasks();
}
