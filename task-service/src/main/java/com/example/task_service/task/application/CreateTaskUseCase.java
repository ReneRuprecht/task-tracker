package com.example.task_service.task.application;

import com.example.task_service.project.application.ProjectRepositoryPort;
import com.example.task_service.project.application.exception.ProjectNotFoundException;
import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.task.application.commands.CreateTaskCommand;
import com.example.task_service.task.domain.RepositoryPort;
import com.example.task_service.task.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateTaskUseCase {

    private final RepositoryPort repository;
    private final ProjectRepositoryPort projectRepositoryPort;

    public Task execute(CreateTaskCommand createTaskCommand) {
        ProjectID projectID = ProjectID.of(createTaskCommand.projectID());

        projectRepositoryPort
                .findByID(projectID)
                .orElseThrow(() -> new ProjectNotFoundException(projectID));

        Task task = Task.create(createTaskCommand.title(), createTaskCommand.projectID());

        return this.repository.save(task);
    }

}
