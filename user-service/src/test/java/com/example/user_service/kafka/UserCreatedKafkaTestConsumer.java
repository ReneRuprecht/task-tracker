package com.example.user_service.kafka;

import com.example.user_service.infrastructure.publish.UserCreatedKafkaMessage;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@TestComponent
public class UserCreatedKafkaTestConsumer {
    private final List<UserCreatedKafkaMessage> messages = new CopyOnWriteArrayList<>();

    @KafkaListener(
            topics = "${app.kafka.topics.user-created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    void consume(UserCreatedKafkaMessage message) {
        messages.add(message);
    }

    public List<UserCreatedKafkaMessage> getMessages() {
        return messages;
    }

    public void clear() {
        messages.clear();
    }
}
