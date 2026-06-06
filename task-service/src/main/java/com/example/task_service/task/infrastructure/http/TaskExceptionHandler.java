package com.example.task_service.task.infrastructure.http;

import com.example.task_service.task.domain.exception.EmptyTaskTitleException;
import com.example.task_service.task.domain.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(EmptyTaskTitleException.class)
    public ResponseEntity<String> handleEmptyTaskNameException() {
        return new ResponseEntity<>("Invalid Request Data", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException() {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
