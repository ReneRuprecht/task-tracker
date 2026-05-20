package com.example.user_service.domain;

import com.example.user_service.domain.exception.InvalidUserEmailException;

public class UserEmail {

    private final String userEmail;

    private UserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public static UserEmail newUserEmail(String userEmail) {
        if (userEmail.isBlank()) {
            throw new InvalidUserEmailException("Email cannot be empty");
        }

        if (!isValid(userEmail)) {
            throw new InvalidUserEmailException("Email is invalid");
        }

        return new UserEmail(userEmail);
    }

    public String toString(){
        return this.userEmail;
    }

    private static boolean isValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
