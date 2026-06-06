package com.example.task_service.task.application;

import com.example.task_service.task.domain.RepositoryPort;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.exception.TaskNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindTaskByIDUseCase {

    private final RepositoryPort repository;

    public Task execute(TaskID id) {

        return this.repository.findByID(id).orElseThrow(TaskNotFoundException::new);
    }
}
