package com.example.user_service.application;

import com.example.user_service.domain.event.UserCreatedEvent;

public interface PublishPort {

    void publish(UserCreatedEvent userCreatedEvent);
}
