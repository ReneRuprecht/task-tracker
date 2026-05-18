package com.example.task_service.unit.application;

import com.example.task_service.application.ListTasksUseCase;
import com.example.task_service.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListTasksUseCaseTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    ListTasksUseCase underTest;


    @Test
    void shouldExecute() {
        underTest.execute();

        verify(repository).findAll();
    }
}
