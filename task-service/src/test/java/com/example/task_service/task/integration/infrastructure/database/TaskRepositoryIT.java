package com.example.task_service.task.integration.infrastructure.database;

import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.task.domain.Task;
import com.example.task_service.task.domain.TaskProjectID;
import com.example.task_service.task.infrastructure.database.JPATaskRepository;
import com.example.task_service.task.infrastructure.database.TaskEntity;
import com.example.task_service.task.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class TaskRepositoryIT {

    @Autowired
    JPATaskRepository jpaRepository;
    @Autowired
    TaskRepository underTest;

    @BeforeEach
    void beforeAll() {
        jpaRepository.deleteAll();
    }

    @Test
    void shouldSaveTask() {

        String title = "test-task";
        UUID projectID = UUID.randomUUID();
        Task task = Task.create(title, projectID);

        underTest.save(task);

        List<TaskEntity> taskEntities = jpaRepository.findAll();
        assertFalse(taskEntities.isEmpty());

        TaskEntity entity = taskEntities.getFirst();
        assertEquals(task.getId().toString(), entity.getId().toString());
    }

    @Test
    void shouldListTasks() {

        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);

        UUID p2ID = UUID.randomUUID();
        Task task2 = Task.create("refactor", p2ID);
        task2.getStatus().close();

        underTest.save(task1);
        underTest.save(task2);

        List<Task> tasks = underTest.findAll();

        assertFalse(tasks.isEmpty());

        Task firstTask = tasks.getFirst();
        assertEquals(task1.getId().toString(), firstTask.getId().toString());
        assertEquals("feature", firstTask.getTitle().toString());
        assertEquals("OPEN", firstTask.getStatus().value().toString());
        assertNotNull(firstTask.getProjectID());

        Task secondTask = tasks.get(1);
        assertEquals(task2.getId().toString(), secondTask.getId().toString());
        assertEquals("refactor", secondTask.getTitle().toString());
        assertEquals("CLOSED", secondTask.getStatus().value().toString());
        assertNotNull(secondTask.getProjectID());
    }

    @Test
    void shouldFindTaskByID() {

        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);

        underTest.save(task1);

        Optional<Task> task = underTest.findByID(task1.getId());

        assertTrue(task.isPresent());

        assertEquals(task1.getId().toString(), task.get().getId().toString());
        assertEquals("feature", task.get().getTitle().toString());
        assertEquals("OPEN", task.get().getStatus().value().toString());

    }

    @Test
    void shouldReturnEmptyOptionalIfFindTaskByIDIsEmpty() {

        UUID p1ID = UUID.randomUUID();
        Task task1 = Task.create("feature", p1ID);

        Optional<Task> task = underTest.findByID(task1.getId());

        assertFalse(task.isPresent());

    }

    @Test
    void shouldReturnTasksByTaskProjectID() {

        ProjectID projectID = ProjectID.newProjectID();
        Task task1 = Task.create("feature", projectID.id());
        Task task2 = Task.create("refactor", projectID.id());

        underTest.save(task1);
        underTest.save(task2);

        List<Task> tasks = underTest.listTasksByProjectID(TaskProjectID.of(projectID.id()));

        assertEquals(2, tasks.size());
        assertThat(tasks)
                .extracting(t -> t.getTitle().toString())
                .containsExactlyInAnyOrder(
                        task1.getTitle().toString(),
                        task2.getTitle().toString()
                );

    }

}
