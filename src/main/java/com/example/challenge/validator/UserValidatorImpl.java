package com.example.challenge.validator;

import com.example.challenge.exception.InvalidFieldException;
import com.example.challenge.model.User;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidatorImpl implements UserValidator {

    @Override
    public boolean isValidEmail(String email) {
        if (email != null) {
            return Pattern.compile("^([\\w\\d_\\-\\.]+)@([\\w\\d_\\-\\.]+)\\.([\\w]{2,5})$")
                    .matcher(email)
                    .matches();
        }
        return false;
    }

    @Override
    public boolean isValidPassword(String password) {
        if (password != null) {
            return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\w\\d]{8,}$")
                    .matcher(password)
                    .matches();
        }
        return false;
    }

    @Override
    public boolean isValidNickname(String nickname) {
        if (nickname != null) {
            return Pattern.compile("^[\\w\\d](?:[-_\\.]?[\\w\\d])*$")
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
            throw new InvalidFieldException("Invalid email!");
        }
        if (!isValidPassword) {
            throw new InvalidFieldException("Invalid password!");
        }
        return true;
    }

    @Override
    public boolean validate(User user) {
        validateEmailPassword(user);
        boolean isValidNickname = isValidNickname(user.getNickname());
        if (!isValidNickname) {
            throw new InvalidFieldException("Invalid nickname!");
        }
        return true;
    }


}
