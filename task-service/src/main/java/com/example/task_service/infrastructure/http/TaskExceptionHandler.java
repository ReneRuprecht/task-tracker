package com.example.task_service.infrastructure.http;

import com.example.task_service.domain.exception.EmptyTaskNameException;
import com.example.task_service.domain.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(EmptyTaskNameException.class)
    public ResponseEntity<String> handleEmptyTaskNameException() {
        return new ResponseEntity<>("Invalid Request Data", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
