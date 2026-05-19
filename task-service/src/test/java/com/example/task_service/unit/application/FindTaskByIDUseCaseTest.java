package com.example.task_service.unit.application;

import com.example.task_service.application.FindTaskByIDUseCase;
import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.TaskStatus;
import com.example.task_service.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    void shouldExecute() {
        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );

        when(repository.findByID(any())).thenReturn(Optional.of(task1));

        underTest.execute(task1.getId());

        ArgumentCaptor<TaskID> taskCaptor = ArgumentCaptor.forClass(TaskID.class);

        verify(repository).findByID(taskCaptor.capture());

        TaskID id = taskCaptor.getValue();

        assertEquals(task1.getId().toString(), id.toString());
    }
}
