package com.example.task_service.project.unit.domain;

import com.example.task_service.project.domain.ProjectName;
import com.example.task_service.project.domain.exception.EmptyProjectNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjectNameTest {

    @Test
    void shouldCreateProjectName() {

        String expected = "add feature";

        ProjectName actual = ProjectName.newProjectName("add feature");

        assertEquals(expected, actual.name());
    }

    @Test
    void shouldThrowEmptyProjectNameException() {

        Exception exception = assertThrows(
                EmptyProjectNameException.class, () -> {
                    ProjectName.newProjectName("");
                }
        );

        String expectedMessage = "ProjectName cannot be empty";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }


}

