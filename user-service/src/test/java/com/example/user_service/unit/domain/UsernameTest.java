package com.example.user_service.unit.domain;

import com.example.user_service.domain.Username;
import com.example.user_service.domain.exception.InvalidUsernameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsernameTest {

    @Test
    void shouldCreateUsername() {
        String name = "test-user";

        Username username = Username.newUserName(name);

        assertEquals("test-user", username.toString());
    }

    @Test
    void shouldThrowInvalidUsernameExceptionIfUsernameIsBlank() {
        String name = "";

        assertThrows(
                InvalidUsernameException.class, () -> {
                    Username.newUserName(name);
                }
        );

    }
}
