package com.example.task_service.unit.application;

import com.example.task_service.application.FindTaskByIDUseCase;
import com.example.task_service.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FindTaskByIDUseCaseTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    FindTaskByIDUseCase underTest;

    @Test
    void shouldExecute() {
        underTest.execute(any());

        verify(repository).findByID(any());
    }
}
