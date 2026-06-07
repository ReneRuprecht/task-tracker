package com.example.task_service.project.application;

import com.example.task_service.project.domain.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateProjectUseCase {

    private final ProjectRepositoryPort repository;

    public Project execute(CreateProjectCommand createProjectCommand) {
        Project project = Project.create(createProjectCommand.name());
        return this.repository.save(project);
    }
}
