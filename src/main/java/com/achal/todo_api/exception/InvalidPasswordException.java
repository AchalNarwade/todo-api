package com.achal.todo_api.exception;



public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message){
        super(message);
    }
}
