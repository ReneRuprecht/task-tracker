package com.example.user_service.domain;

import java.util.UUID;

public class UserID {

    private final UUID userID;

    private UserID(UUID userID) {
        this.userID = userID;
    }

    public static UserID newUserID() {

        UUID userID = UUID.randomUUID();
        return new UserID(userID);
    }

    public String toString() {
        return this.userID.toString();
    }

    public UUID value() {
        return this.userID;
    }
}
