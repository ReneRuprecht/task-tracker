package com.example.task_service.project.application;


import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.task.domain.RepositoryPort;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskProjectID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ListTasksByProjectIDUseCase {

    private final RepositoryPort repository;

    public List<Task> execute(ProjectID projectID) {

        return repository.listTasksByProjectID(TaskProjectID.of(projectID.id()));
    }
}
