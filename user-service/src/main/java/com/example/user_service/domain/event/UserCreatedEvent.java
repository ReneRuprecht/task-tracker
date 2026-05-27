package com.example.user_service.domain.event;

import com.example.user_service.domain.UserEmail;
import com.example.user_service.domain.UserID;
import com.example.user_service.domain.Username;

public record UserCreatedEvent(UserID userID, Username username, UserEmail userEmail) {
}
