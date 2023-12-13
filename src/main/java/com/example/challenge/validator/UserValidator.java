package com.example.challenge.validator;

import com.example.challenge.model.User;

public interface UserValidator extends Validator<User> {
    boolean isValidEmail(String email);

    boolean isValidPassword(String password);

    boolean isValidNickname(String nickname);

    boolean validateEmailPassword(User user);
}
