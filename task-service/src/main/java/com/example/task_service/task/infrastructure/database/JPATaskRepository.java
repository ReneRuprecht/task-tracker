package com.example.task_service.task.infrastructure.database;

import com.example.task_service.project.domain.ProjectID;
import com.example.task_service.task.domain.TaskProjectID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface JPATaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findAllByProjectID(UUID projectID);
}
