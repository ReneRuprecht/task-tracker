package com.example.user_service.application;

import com.example.user_service.domain.RepositoryPort;
import com.example.user_service.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final RepositoryPort repositoryPort;

    public void execute(User user) {
        this.repositoryPort.save(user);
    }

}
