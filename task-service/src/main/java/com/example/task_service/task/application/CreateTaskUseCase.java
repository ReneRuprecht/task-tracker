package com.example.task_service.task.application;

import com.example.task_service.task.domain.RepositoryPort;
import com.example.task_service.task.domain.Task;
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
