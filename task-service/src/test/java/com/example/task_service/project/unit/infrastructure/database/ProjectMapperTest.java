package com.example.task_service.project.unit.infrastructure.database;

import com.example.task_service.project.domain.Project;
import com.example.task_service.project.infrastructure.database.ProjectEntity;
import com.example.task_service.project.infrastructure.database.ProjectMapper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectMapperTest {

    @Test
    void shouldMapDomainProjectToProjectEntity() {
        Project project = Project.create("feature");

        ProjectEntity entity = ProjectMapper.toEntity(project);

        assertEquals(project.getId().toString(), entity.getId().toString());
        assertEquals(project.getName().name(), entity.getName());
    }

    @Test
    void shouldMapProjectEntityToProject() {
        ProjectEntity entity = new ProjectEntity(UUID.randomUUID(), "feature");

        Project project = ProjectMapper.toDomain(entity);


        assertEquals(entity.getId().toString(), project.getId().toString());
        assertEquals(entity.getName(), project.getName().name());
    }
}
