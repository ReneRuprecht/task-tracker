package com.example.user_service.unit.domain;

import com.example.user_service.domain.UserID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserIDTest {

    @Test
    void shouldCreateUserID() {
        UserID userID = UserID.newUserID();

        assertFalse(userID.toString().isBlank());
    }
}
