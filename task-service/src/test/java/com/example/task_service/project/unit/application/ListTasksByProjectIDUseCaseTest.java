package com.example.task_service.project.unit.application;

import com.example.task_service.project.application.ListTasksByProjectIDUseCase;
import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskProjectID;
import com.example.task_service.task.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListTasksByProjectIDUseCaseTest {

    @Mock
    TaskRepository repository;

    @InjectMocks
    ListTasksByProjectIDUseCase underTest;

    @Test
    void shouldListTasksByProjectID() {

        ProjectID projectID = ProjectID.newProjectID();
        Task task1 = Task.create("task1", projectID.id());
        Task task2 = Task.create("task2", projectID.id());

        when(repository.listTasksByProjectID(any(TaskProjectID.class))).thenReturn(List.of(
                task1,
                task2
        ));

        ArgumentCaptor<TaskProjectID> taskProjectIDArgumentCaptor = ArgumentCaptor.forClass(
                TaskProjectID.class);

        List<Task> tasks = underTest.execute(projectID);

        verify(repository).listTasksByProjectID(taskProjectIDArgumentCaptor.capture());

        assertEquals(projectID.toString(), taskProjectIDArgumentCaptor.getValue().toString());

        assertEquals(2, tasks.size());
        assertThat(tasks)
                .extracting(t -> t.getTitle().toString())
                .containsExactlyInAnyOrder(
                        task1.getTitle().toString(),
                        task2.getTitle().toString()
                );
    }
}
