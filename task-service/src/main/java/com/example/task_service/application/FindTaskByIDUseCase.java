package com.example.task_service.application;

import com.example.task_service.domain.RepositoryPort;
import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindTaskByIDUseCase {

    private final RepositoryPort repository;

    public Task execute(TaskID id) {

        return this.repository.findByID(id);
    }
}
