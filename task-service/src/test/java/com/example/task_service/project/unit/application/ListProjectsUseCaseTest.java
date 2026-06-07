package com.example.task_service.project.unit.application;

import com.example.task_service.project.application.ListProjectsUseCase;
import com.example.task_service.project.domain.Project;
import com.example.task_service.project.infrastructure.database.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListProjectsUseCaseTest {

    @Mock
    ProjectRepository repository;

    @InjectMocks
    ListProjectsUseCase undertest;

    @Test
    void shouldListProjects() {

        Project p1 = Project.create("My Project 1");
        Project p2 = Project.create("My Project 2");

        when(undertest.execute()).thenReturn(List.of(p1, p2));

        List<Project> actual = undertest.execute();

        verify(repository).findAll();

        assertEquals(2, actual.size());

        Project actualP1 = actual.getFirst();
        assertNotNull(actualP1.getId());
        assertEquals("My Project 1", actualP1.getName().name());

        Project actualP2 = actual.get(1);
        assertNotNull(actualP2.getId());
        assertEquals("My Project 2", actualP2.getName().name());
    }
}
