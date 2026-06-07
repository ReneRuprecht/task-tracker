package com.example.task_service.project.unit.domain;

import com.example.task_service.project.domain.ProjectID;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectIDTest {

    @Test
    void shouldCreateProjectID() {

        ProjectID id = ProjectID.newProjectID();
        assertNotEquals("", id.toString());
    }

    @Test
    void shouldCreateProjectIDWhenCalledWithUUID() {

        UUID uuid = UUID.randomUUID();
        ProjectID actual = ProjectID.of(uuid);
        assertNotNull(actual.id());
        assertFalse(actual.toString().isEmpty());
        assertEquals(uuid.toString(), actual.toString());
    }
}
