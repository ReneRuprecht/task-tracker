package com.example.user_service.infrastructure.http;

import com.example.user_service.domain.exception.InvalidUserEmailException;
import com.example.user_service.domain.exception.InvalidUsernameException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class UserControllerExceptionHandler {

    @ExceptionHandler({InvalidUsernameException.class, InvalidUserEmailException.class})
    public ResponseEntity<Map<String, String>> handleInvalidDomain() {
        return ResponseEntity.badRequest().body(Map.of("message", "Invalid Request Data"));
    }
}
