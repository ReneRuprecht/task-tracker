package com.example.task_service.infrastructure.http;

import com.example.task_service.domain.exception.EmptyTaskNameException;
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
}
