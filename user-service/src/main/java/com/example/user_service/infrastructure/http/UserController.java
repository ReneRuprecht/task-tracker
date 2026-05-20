package com.example.user_service.infrastructure.http;

import com.example.user_service.application.CreateUserUseCase;
import com.example.user_service.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {

        User user = UserMapper.toUser(createUserRequest);

        this.createUserUseCase.execute(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
