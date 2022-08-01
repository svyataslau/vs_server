package com.example.vs_server.validator;

import com.example.vs_server.model.User;
import java.util.regex.Pattern;

public class UserValidator implements ValidatorInterface<User> {

    public static boolean isValidEmail(String email) {
        if (email != null) {
            return Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
                    .matcher(email)
                    .matches();
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        if (password != null) {
            return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
                    .matcher(password)
                    .matches();
        }
        return false;
    }

    public static boolean isValidNickname(String nickname) {
        if (nickname != null) {
            return Pattern.compile("^[a-z0-9](?:[-_\\.]?[a-z0-9])*$")
                    .matcher(nickname)
                    .matches();
        }
        return false;
    }

    @Override
    public boolean validate(User user) {
        if(isValidEmail(user.getEmail()) && isValidPassword(user.getPassword()) && isValidNickname(user.getNickname())) {
            return true;
        }
        return false;
    }
}
