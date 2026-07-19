package com.achal.todo_api.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message){
        super(message);
    }
}
