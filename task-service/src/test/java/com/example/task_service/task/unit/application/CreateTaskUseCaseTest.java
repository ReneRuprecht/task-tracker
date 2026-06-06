package com.example.task_service.task.unit.application;

import com.example.task_service.task.application.CreateTaskUseCase;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskID;
import com.example.task_service.task.domain.TaskTitle;
import com.example.task_service.task.domain.TaskStatus;
import com.example.task_service.task.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateTaskUseCaseTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    CreateTaskUseCase underTest;


    @Test
    void shouldExecute() {
        TaskID id = TaskID.newTaskID();
        TaskTitle title = TaskTitle.newTaskTitle("test-task");
        TaskStatus status = TaskStatus.newTaskStatus();
        Task task = new Task(id, title, status);

        underTest.execute(task);

        verify(repository).save(task);
    }
}
