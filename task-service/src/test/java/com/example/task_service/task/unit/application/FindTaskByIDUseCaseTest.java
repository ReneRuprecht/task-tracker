package com.example.task_service.task.unit.application;

import com.example.task_service.task.application.FindTaskByIDUseCase;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindTaskByIDUseCaseTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    FindTaskByIDUseCase underTest;

    @Test
    void shouldFindTaskByID() {
        Task task = Task.create("feature", UUID.randomUUID());

        when(repository.findByID(any())).thenReturn(Optional.of(task));

        underTest.execute(task.getId());

        ArgumentCaptor<TaskID> taskCaptor = ArgumentCaptor.forClass(TaskID.class);

        verify(repository).findByID(taskCaptor.capture());

        TaskID id = taskCaptor.getValue();

        assertEquals(task.getId().toString(), id.toString());
    }
}
