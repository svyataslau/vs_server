package com.example.vs_server.validator;

import com.example.vs_server.exception.InvalidEmailException;
import com.example.vs_server.exception.InvalidPasswordException;
import com.example.vs_server.model.User;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidatorImpl implements Validator<User>, UserValidator {

    @Override
    public boolean isValidEmail(String email) {
        if (email != null) {
            return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
                    .matcher(email)
                    .matches();
        }
        return false;
    }

    @Override
    public boolean isValidPassword(String password) {
        if (password != null) {
            return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
                    .matcher(password)
                    .matches();
        }
        return false;
    }

    @Override
    public boolean isValidNickname(String nickname) {
        if (nickname != null) {
            return Pattern.compile("^[a-z0-9](?:[-_\\.]?[a-z0-9])*$")
                    .matcher(nickname)
                    .matches();
        }
        return false;
    }

    @Override
    public boolean validateEmailPassword(User user) {
        boolean isValidEmail = isValidEmail(user.getEmail());
        boolean isValidPassword = isValidPassword(user.getPassword());
        if (!isValidEmail) {
            throw new InvalidEmailException("Invalid email!");
        }
        if (!isValidPassword) {
            throw new InvalidPasswordException("Invalid password!");
        }
        return true;
    }

    @Override
    public boolean validate(User user) {
        if (isValidEmail(user.getEmail()) &&
                isValidPassword(user.getPassword()) &&
                isValidNickname(user.getNickname())) {
            return true;
        }
        return false;
    }


}
