package com.example.user_service.unit.infrastructure.publish;

import com.example.user_service.domain.User;
import com.example.user_service.domain.UserEmail;
import com.example.user_service.domain.UserID;
import com.example.user_service.domain.Username;
import com.example.user_service.domain.event.UserCreatedEvent;
import com.example.user_service.infrastructure.publish.UserCreatedKafkaMessage;
import com.example.user_service.infrastructure.publish.UserMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserMapperTest {

    @Test
    void shouldReturnUserCreatedKafkaMessage() {

        User user = new User(
                UserID.newUserID(),
                Username.newUserName("user1"),
                UserEmail.newUserEmail("user1@test.de")
        );

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(
                user.getUserid(),
                user.getUsername(),
                user.getUserEmail()
        );

        UserCreatedKafkaMessage actual = UserMapper.toMessage(userCreatedEvent);

        assertFalse(actual.userID().isBlank());
        assertEquals("user1", actual.username());
        assertEquals("user1@test.de", actual.userEmail());

    }
}
