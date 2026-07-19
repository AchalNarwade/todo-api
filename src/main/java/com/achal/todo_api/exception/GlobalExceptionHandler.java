package com.achal.todo_api.exception;


import com.achal.todo_api.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice //register this class as global exception handler
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message" , ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        //extract all validation error from the exception
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String,String>> handleInvalidPasswordException(InvalidPasswordException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public  ResponseEntity<Map<String,String>> handleUserNotFoundException(UserNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleTaskNotFoundException(TaskNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message",ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

}
