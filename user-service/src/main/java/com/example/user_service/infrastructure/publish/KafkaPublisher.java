package com.example.user_service.infrastructure.publish;

import com.example.user_service.application.PublishPort;
import com.example.user_service.domain.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaPublisher implements PublishPort {
    private final KafkaTemplate<String, UserCreatedKafkaMessage> kafkaTemplate;

    @Value("${app.kafka.topics.user-created}")
    private String userCreatedTopic;

    @Override
    public void publish(UserCreatedEvent userCreatedEvent) {


        UserCreatedKafkaMessage message = UserMapper.toMessage(userCreatedEvent);
        kafkaTemplate.send(userCreatedTopic, userCreatedEvent.userID().toString(), message);

    }
}
