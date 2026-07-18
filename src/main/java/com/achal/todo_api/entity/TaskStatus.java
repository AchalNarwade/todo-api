package com.achal.todo_api.entity;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    PENDING,
    COMPLETED

    // the database will store only these values preventing invalid statuses
}
