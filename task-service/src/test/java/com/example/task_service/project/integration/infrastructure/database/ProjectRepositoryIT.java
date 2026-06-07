package com.example.task_service.project.integration.infrastructure.database;

import com.example.task_service.project.domain.Project;
import com.example.task_service.project.infrastructure.database.JPAProjectRepository;
import com.example.task_service.project.infrastructure.database.ProjectEntity;
import com.example.task_service.project.infrastructure.database.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProjectRepositoryIT {

    @Autowired
    JPAProjectRepository jpaRepository;
    @Autowired
    ProjectRepository underTest;

    @BeforeEach
    void beforeAll() {
        jpaRepository.deleteAll();
    }

    @Test
    void shouldSaveProject() {

        Project project = Project.create("refactor");

        underTest.save(project);

        List<ProjectEntity> projectEntities = jpaRepository.findAll();
        assertFalse(projectEntities.isEmpty());

        ProjectEntity entity = projectEntities.getFirst();
        assertEquals(project.getId().id().toString(), entity.getId().toString());
        assertEquals(project.getName().name(), entity.getName());
    }

    @Test
    void shouldListProjects() {

        Project project1 = Project.create("feature");
        Project project2 = Project.create("refactor");

        underTest.save(project1);
        underTest.save(project2);

        List<Project> projects = underTest.findAll();

        assertFalse(projects.isEmpty());

        Project firstProject = projects.getFirst();
        assertEquals(project1.getId().toString(), firstProject.getId().toString());
        assertEquals("feature", firstProject.getName().name());

        Project secondProject = projects.get(1);
        assertEquals(project2.getId().toString(), secondProject.getId().toString());
        assertEquals("refactor", secondProject.getName().name());
    }

    @Test
    void shouldFindProjectByID() {

        Project project = Project.create("feature");

        underTest.save(project);

        Optional<Project> actual = underTest.findByID(project.getId());

        assertTrue(actual.isPresent());

        assertEquals(project.getId().toString(), actual.get().getId().toString());
        assertEquals("feature", actual.get().getName().name());

    }

    @Test
    void shouldReturnEmptyOptionalIfFindTaskByIDIsEmpty() {

        Project project = Project.create("feature");

        Optional<Project> actual = underTest.findByID(project.getId());

        assertFalse(actual.isPresent());

    }


}
