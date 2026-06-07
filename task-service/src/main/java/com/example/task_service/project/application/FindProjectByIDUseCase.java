package com.example.task_service.project.application;

import com.example.task_service.project.application.exception.ProjectNotFoundException;
import com.example.task_service.project.domain.Project;
import com.example.task_service.project.domain.ProjectID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindProjectByIDUseCase {

    private final ProjectRepositoryPort repository;

    public Project execute(ProjectID id) {
        return this.repository.findByID(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }
}
