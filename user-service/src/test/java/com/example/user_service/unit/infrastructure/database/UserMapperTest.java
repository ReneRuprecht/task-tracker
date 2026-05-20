package com.example.user_service.unit.infrastructure.database;


import com.example.user_service.domain.User;
import com.example.user_service.domain.UserEmail;
import com.example.user_service.domain.UserID;
import com.example.user_service.domain.Username;
import com.example.user_service.infrastructure.database.UserEntity;
import com.example.user_service.infrastructure.database.UserMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserMapperTest {

    @Test
    void shouldReturnUserEntity() {

        User user = new User(
                UserID.newUserID(),
                Username.newUserName("user1"),
                UserEmail.newUserEmail("user1@test.de")
        );

        UserEntity entity = UserMapper.toEntity(user);

        assertFalse(entity.getId().toString().isBlank());
        assertEquals("user1", entity.getUsername());
        assertEquals("user1@test.de", entity.getEmail());
    }
}
