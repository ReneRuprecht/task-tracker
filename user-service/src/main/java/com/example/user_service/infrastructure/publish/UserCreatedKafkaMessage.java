package com.example.user_service.infrastructure.publish;

public record UserCreatedKafkaMessage(String userID, String username, String userEmail) {
}
