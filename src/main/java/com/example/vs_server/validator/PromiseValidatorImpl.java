package com.example.vs_server.validator;

import com.example.vs_server.exception.InvalidFieldException;
import com.example.vs_server.model.Promise;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PromiseValidatorImpl implements PromiseValidator {

    @Override
    public boolean isValidTitle(String title) {
        if (title != null) {
            return Pattern.compile("^[\\w\\d\\s](?:[-_\\.]?[\\w\\d\\s])*$")
                    .matcher(title)
                    .matches();
        }
        return false;
    }

    @Override
    public boolean validate(Promise promise) {
        if (!isValidTitle(promise.getTitle())) {
            throw new InvalidFieldException("Title can contain latin letters, numbers, space -_.");
        }
        if (promise.getTitle().length() < 0 || promise.getTitle().length() > 128) {
            throw new InvalidFieldException("Title must not be empty and be longer than 128 characters!");
        }
        return true;
    }
}
