package com.example.task_service.project.application;

import com.example.task_service.project.domain.Project;
import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.task.domain.Task;

import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryPort {

    Project save(Project project);

    List<Project> findAll();

    Optional<Project> findByID(ProjectID id);
}
