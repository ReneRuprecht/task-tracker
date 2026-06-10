package com.example.task_service.task.unit.application;

import com.example.task_service.task.application.PatchTaskUseCase;
import com.example.task_service.task.application.commands.PatchTaskCommand;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.exception.TaskNotFoundException;
import com.example.task_service.task.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatchTaskUseCaseTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    PatchTaskUseCase underTest;


    @Test
    void shouldPatchTaskWithAllFields() {
        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);

        PatchTaskCommand patchTaskCommand = new PatchTaskCommand(
                task1.getId().id(),
                Optional.of("refactor"),
                Optional.of("closed")
        );

        when(repository.findByID(any())).thenReturn(Optional.of(task1));

        underTest.execute(patchTaskCommand);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);

        verify(repository).findByID(any());
        verify(repository).save(taskCaptor.capture());

        Task savedTask = taskCaptor.getValue();

        assertEquals("refactor", savedTask.getTitle().toString());
        assertEquals("CLOSED", savedTask.getStatus().value().toString());
    }

    @Test
    void shouldNotPatchTaskWithEmptyFields() {
        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);

        PatchTaskCommand patchTaskCommand = new PatchTaskCommand(
                task1.getId().id(),
                Optional.empty(),
                Optional.empty()
        );

        when(repository.findByID(any())).thenReturn(Optional.of(task1));
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);

        underTest.execute(patchTaskCommand);

        verify(repository).findByID(any(TaskID.class));
        verify(repository).save(captor.capture());

        Task patchedTask = captor.getValue();

        assertEquals("feature", patchedTask.getTitle().toString());
        assertEquals("OPEN", patchedTask.getStatus().value().toString());
    }

    @Test
    void shouldThrowTaskNotFoundIfTaskNotExists() {

        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);

        PatchTaskCommand patchTaskCommand = new PatchTaskCommand(
                task1.getId().id(),
                Optional.of("refactor"),
                Optional.of("closed")
        );
        when(repository.findByID(any())).thenReturn(Optional.empty());
        assertThrows(
                TaskNotFoundException.class, () -> {
                    underTest.execute(patchTaskCommand);
                }
        );

    }
}
