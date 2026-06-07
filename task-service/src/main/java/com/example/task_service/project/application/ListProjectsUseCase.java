package com.example.task_service.project.application;

import com.example.task_service.project.domain.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ListProjectsUseCase {
    private final ProjectRepositoryPort repository;

    public List<Project> execute() {
        return this.repository.findAll();
    }

}
