package com.example.task_service.unit.application;

import com.example.task_service.application.PatchTask;
import com.example.task_service.application.PatchTaskUseCase;
import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.TaskStatus;
import com.example.task_service.domain.exception.TaskNotFoundException;
import com.example.task_service.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    void shouldExecuteWithAllPatchTaskFields() {
        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );
        PatchTask patchTask = new PatchTask(task1.getId().toString(), "refactor", "closed");

        when(repository.findByID(any())).thenReturn(Optional.of(task1));

        underTest.execute(patchTask);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);

        verify(repository).findByID(any());
        verify(repository).save(taskCaptor.capture());

        Task savedTask = taskCaptor.getValue();

        assertEquals("refactor", savedTask.getName().toString());
        assertEquals("CLOSED", savedTask.getStatus().value().toString());
    }

    @Test
    void shouldExecuteWithEmptyPatchTaskFields() {
        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );
        PatchTask patchTask = new PatchTask(task1.getId().toString(), "", "");

        when(repository.findByID(any())).thenReturn(Optional.of(task1));

        underTest.execute(patchTask);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);

        verify(repository).findByID(any());
        verify(repository).save(taskCaptor.capture());

        Task savedTask = taskCaptor.getValue();

        assertEquals("feature", savedTask.getName().toString());
        assertEquals("OPEN", savedTask.getStatus().value().toString());
    }

    @Test
    void shouldThrowTaskNotFoundIfTaskNotExists() {

        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );
        PatchTask patchTask = new PatchTask(task1.getId().toString(), "refactor", "closed");
        when(repository.findByID(any())).thenReturn(Optional.empty());
        assertThrows(
                TaskNotFoundException.class, () -> {
                    underTest.execute(patchTask);
                }
        );

    }
}
