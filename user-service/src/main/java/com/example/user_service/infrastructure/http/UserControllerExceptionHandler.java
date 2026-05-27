package com.example.user_service.infrastructure.http;

import com.example.user_service.domain.exception.InvalidUserEmailException;
import com.example.user_service.domain.exception.InvalidUsernameException;
import com.example.user_service.domain.exception.UserEmailAlreadyExistsException;
import com.example.user_service.domain.exception.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler({UsernameAlreadyExistsException.class, UserEmailAlreadyExistsException.class})
    public ResponseEntity<Map<String, String>> handleAlreadyExists(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", ex.getMessage()));
    }
}
