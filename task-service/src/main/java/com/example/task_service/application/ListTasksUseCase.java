package com.example.task_service.application;

import com.example.task_service.domain.RepositoryPort;
import com.example.task_service.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ListTasksUseCase {

    private final RepositoryPort repository;

    public List<Task> execute() {
        return this.repository.findAll();
    }

}
