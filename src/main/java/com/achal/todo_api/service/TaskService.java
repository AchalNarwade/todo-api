package com.achal.todo_api.service;

import com.achal.todo_api.dto.task.CreateTaskRequest;
import com.achal.todo_api.dto.task.TaskResponse;

public interface TaskService {

    TaskResponse createTask(CreateTaskRequest createTaskRequest);
}
