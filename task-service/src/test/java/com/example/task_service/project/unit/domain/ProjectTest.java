package com.example.task_service.project.unit.domain;

import com.example.task_service.project.domain.Project;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProjectTest {

    @Test
    void shouldCreateProject() {

        String projectName = "add feature";

        Project actual = Project.create(projectName);

        assertEquals(projectName, actual.getName().name());
        assertFalse(actual.getId().toString().isEmpty());
    }

}
