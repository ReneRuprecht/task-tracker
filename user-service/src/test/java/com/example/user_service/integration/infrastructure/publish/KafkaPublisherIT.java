package com.example.user_service.integration.infrastructure.publish;

import com.example.user_service.domain.UserEmail;
import com.example.user_service.domain.UserID;
import com.example.user_service.domain.Username;
import com.example.user_service.domain.event.UserCreatedEvent;
import com.example.user_service.infrastructure.publish.KafkaPublisher;
import com.example.user_service.infrastructure.publish.UserCreatedKafkaMessage;
import com.example.user_service.kafka.UserCreatedKafkaTestConsumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.Duration;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@ActiveProfiles("test")
@Import(UserCreatedKafkaTestConsumer.class)
class KafkaPublisherIT {

    static String topic = "user-created-test-" + UUID.randomUUID();

    @Autowired
    private KafkaPublisher underTest;

    @Autowired
    private UserCreatedKafkaTestConsumer consumer;

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("app.kafka.topics.user-created", () -> topic);
    }

    @BeforeEach
    void clearMessages() {
        consumer.clear();
    }

    @Test
    void shouldPublishUserCreatedEventToKafka() {

        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(
                UserID.newUserID(),
                Username.newUserName("user1"),
                UserEmail.newUserEmail("user1@test.de")
        );

        underTest.publish(userCreatedEvent);

        await()
                .atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> assertThat(consumer.getMessages()).isNotEmpty());

        UserCreatedKafkaMessage message = consumer.getMessages().getFirst();

        assertThat(message.username()).isEqualTo(userCreatedEvent.username().toString());
    }

}
