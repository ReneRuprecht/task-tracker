package com.example.task_service.test;

import com.example.task_service.project.infrastructure.database.JPAProjectRepository;
import com.example.task_service.task.infrastructure.database.JPATaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("test")
@RestController
@AllArgsConstructor
public class TestResetController {

    private final JPATaskRepository taskRepository;
    private final JPAProjectRepository projectRepository;


    @PostMapping("/test/reset")
    public void reset() {
        taskRepository.deleteAll();
        projectRepository.deleteAll();
    }

}
