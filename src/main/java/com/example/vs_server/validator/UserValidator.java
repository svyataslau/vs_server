package com.example.vs_server.validator;

import com.example.vs_server.model.User;

public interface UserValidator {
    boolean isValidEmail(String email);

    boolean isValidPassword(String password);

    boolean isValidNickname(String nickname);

    boolean validateEmailPassword(User user);
}
