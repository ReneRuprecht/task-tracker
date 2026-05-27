package com.example.user_service.infrastructure.publish;

import com.example.user_service.domain.event.UserCreatedEvent;

public class UserMapper {

    public static UserCreatedKafkaMessage toMessage(UserCreatedEvent userCreatedEvent) {

        return new UserCreatedKafkaMessage(
                userCreatedEvent.userID().toString(),
                userCreatedEvent.username().toString(),
                userCreatedEvent.userEmail().toString()
        );

    }
}
