package com.example.task_service.integration.infrastructure.database;

import com.example.task_service.domain.Task;
import com.example.task_service.domain.TaskID;
import com.example.task_service.domain.TaskName;
import com.example.task_service.domain.TaskStatus;
import com.example.task_service.infrastructure.database.JPATaskRepository;
import com.example.task_service.infrastructure.database.TaskEntity;
import com.example.task_service.infrastructure.database.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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


}
