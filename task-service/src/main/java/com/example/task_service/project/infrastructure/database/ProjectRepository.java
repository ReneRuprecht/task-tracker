package com.example.task_service.project.infrastructure.database;

import com.example.task_service.project.application.ProjectRepositoryPort;
import com.example.task_service.project.domain.Project;
import com.example.task_service.project.domain.ProjectID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectRepository implements ProjectRepositoryPort {

    private final JPAProjectRepository jpaProjectRepository;

    public Project save(Project project) {
        ProjectEntity entity = ProjectMapper.toEntity(project);
        ProjectEntity savedProject = this.jpaProjectRepository.save(entity);
        return ProjectMapper.toDomain(savedProject);
    }

    @Override
    public List<Project> findAll() {
        return this.jpaProjectRepository.findAll().stream().map(ProjectMapper::toDomain).toList();
    }

    @Override
    public Optional<Project> findByID(ProjectID id) {
        return this.jpaProjectRepository.findById(id.id()).map(ProjectMapper::toDomain);
    }
}
