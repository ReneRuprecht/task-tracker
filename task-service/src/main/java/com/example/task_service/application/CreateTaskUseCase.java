package com.example.task_service.application;

import com.example.task_service.domain.RepositoryPort;
import com.example.task_service.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateTaskUseCase {

    private final RepositoryPort repository;

    public void execute(Task task) {
        this.repository.save(task);
    }

}
