package com.example.task_service.task.unit.application;

import com.example.task_service.project.domain.Project;
import com.example.task_service.project.infrastructure.database.ProjectRepository;
import com.example.task_service.task.application.CreateTaskUseCase;
import com.example.task_service.task.application.commands.CreateTaskCommand;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTaskUseCaseTest {

    @Mock
    TaskRepository repository;

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    CreateTaskUseCase underTest;


    @Test
    void shouldCreateTask() {
        UUID id = UUID.randomUUID();
        CreateTaskCommand createTaskCommand = new CreateTaskCommand("test-task", id);

        Task task = Task.create("test-task", id);

        Project project = Project.create("test");

        when(projectRepository.findByID(any())).thenReturn(Optional.of(project));
        when(repository.save(any(Task.class))).thenReturn(task);

        Task saved = underTest.execute(createTaskCommand);

        verify(repository).save(any(Task.class));

        assertNotNull(saved.getId().id());
        assertNotNull(saved.getProjectID().id());
        assertEquals("test-task", saved.getTitle().toString());
    }
}
