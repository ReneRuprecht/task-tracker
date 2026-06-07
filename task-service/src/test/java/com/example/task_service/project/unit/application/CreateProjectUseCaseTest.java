package com.example.task_service.project.unit.application;

import com.example.task_service.project.application.CreateProjectCommand;
import com.example.task_service.project.application.CreateProjectUseCase;
import com.example.task_service.project.domain.Project;
import com.example.task_service.project.infrastructure.database.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateProjectUseCaseTest {

    @Mock
    ProjectRepository repository;

    @InjectMocks
    CreateProjectUseCase underTest;

    @Test
    void shouldCreateAndSaveProject() {
        CreateProjectCommand command =
                new CreateProjectCommand("feature");

        Project savedProject = Project.create("feature");

        when(repository.save(any(Project.class)))
                .thenReturn(savedProject);

        Project actual = underTest.execute(command);

        verify(repository).save(any(Project.class));

        assertEquals("feature", actual.getName().name());
        assertNotNull(actual.getId());
    }
}
