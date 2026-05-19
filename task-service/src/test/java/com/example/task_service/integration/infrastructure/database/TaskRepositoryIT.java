package com.example.task_service.integration.infrastructure.database;

import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.TaskStatus;
import com.example.task_service.domain.exception.TaskNotFoundException;
import com.example.task_service.infrastructure.database.JPATaskRepository;
import com.example.task_service.infrastructure.database.TaskEntity;
import com.example.task_service.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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

        TaskID id = TaskID.newTaskID();
        TaskName name = TaskName.newTaskName("test-task");
        TaskStatus status = TaskStatus.newTaskStatus();
        Task task = new Task(id, name, status);

        underTest.save(task);

        List<TaskEntity> taskEntities = jpaRepository.findAll();
        assertFalse(taskEntities.isEmpty());

        TaskEntity entity = taskEntities.getFirst();
        assertEquals(id.toString(), entity.getId().toString());
    }

    @Test
    void shouldListTasks() {

        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );
        Task task2 = new Task(
                TaskID.newTaskID(), TaskName.newTaskName("refactor"), TaskStatus.fromStatus(
                TaskStatus.Status.CLOSED)
        );

        underTest.save(task1);
        underTest.save(task2);

        List<Task> tasks = underTest.findAll();

        assertFalse(tasks.isEmpty());

        Task firstTask = tasks.getFirst();
        assertEquals(task1.getId().toString(), firstTask.getId().toString());
        assertEquals("feature", firstTask.getName().toString());
        assertEquals(
                "OPEN",
                firstTask.getStatus().value().toString()
        );

        Task secondTask = tasks.get(1);
        assertEquals(task2.getId().toString(), secondTask.getId().toString());
        assertEquals("refactor", secondTask.getName().toString());
        assertEquals(
                "CLOSED",
                secondTask.getStatus().value().toString()
        );
    }

    @Test
    void shouldFindTaskByID() {

        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );

        underTest.save(task1);

        Task task = underTest.findByID(task1.getId());

        assertEquals(task1.getId().toString(), task.getId().toString());
        assertEquals("feature", task.getName().toString());
        assertEquals(
                "OPEN",
                task.getStatus().value().toString()
        );

    }

    @Test
    void shouldThrowTaskNotFoundIfFindTaskByIDIsEmpty() {

        Task task1 = new Task(
                TaskID.newTaskID(),
                TaskName.newTaskName("feature"),
                TaskStatus.newTaskStatus()
        );

        assertThrows(
                TaskNotFoundException.class, () -> {
                    underTest.findByID(task1.getId());
                }
        );

    }


}
