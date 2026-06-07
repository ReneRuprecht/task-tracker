package com.example.task_service.project.unit.application;

import com.example.task_service.project.application.FindProjectByIDUseCase;
import com.example.task_service.project.application.exception.ProjectNotFoundException;
import com.example.task_service.project.domain.Project;
import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.project.infrastructure.database.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindProjectByIDUseCaseTest {

    @Mock
    ProjectRepository repository;

    @InjectMocks
    FindProjectByIDUseCase underTest;

    @Test
    void shouldFindProjectByID() {

        Project p1 = Project.create("My Project 1");

        when(repository.findByID(p1.getId())).thenReturn(Optional.of(p1));

        Project actual = underTest.execute(p1.getId());

        verify(repository).findByID(p1.getId());
        assertEquals("My Project 1", actual.getName().name());
    }

    @Test
    void shouldThrowProjectNotFoundExceptionWhenProjectNotExists() {

        ProjectID id = ProjectID.newProjectID();
        String expectedMessage = String.format("Project with id: %s does not exist", id);

        when(repository.findByID(id)).thenReturn(Optional.empty());

        ProjectNotFoundException actual = assertThrows(
                ProjectNotFoundException.class, () -> {
                    underTest.execute(id);
                }
        );

        verify(repository).findByID(id);

        assertEquals(expectedMessage, actual.getMessage());
        assertNotNull(actual.getProjectID());
    }
}
