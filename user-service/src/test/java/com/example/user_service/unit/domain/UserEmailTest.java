package com.example.user_service.unit.domain;

import com.example.user_service.domain.UserEmail;
import com.example.user_service.domain.exception.InvalidUserEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserEmailTest {

    @Test
    void shouldCreateUserEmail() {
        String email = "test@test.de";

        UserEmail userEmail = UserEmail.newUserEmail(email);

        assertEquals("test@test.de", userEmail.toString());
    }

    @Test
    void shouldThrowInvalidUserEmailExceptionIfEmailIsNull() {

        Exception exception = assertThrows(
                InvalidUserEmailException.class, () -> {
                    UserEmail.newUserEmail(null);
                }
        );

        assertEquals("Email cannot be empty", exception.getMessage());
    }

    @Test
    void shouldThrowInvalidUserEmailExceptionIfEmailIsEmpty() {
        String email = "";


        Exception exception = assertThrows(
                InvalidUserEmailException.class, () -> {
                    UserEmail.newUserEmail(email);
                }
        );

        assertEquals("Email cannot be empty", exception.getMessage());
    }


    @Test
    void shouldThrowInvalidUserEmailExceptionIfEmailIsInvalid() {
        String email = "invalid";


        Exception exception = assertThrows(
                InvalidUserEmailException.class, () -> {
                    UserEmail.newUserEmail(email);
                }
        );

        assertEquals("Email is invalid", exception.getMessage());
    }
}
