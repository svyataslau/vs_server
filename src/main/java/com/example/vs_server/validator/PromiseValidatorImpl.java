package com.example.vs_server.validator;

import com.example.vs_server.exception.InvalidFieldException;
import com.example.vs_server.model.Promise;
import org.springframework.stereotype.Component;

@Component
public class PromiseValidatorImpl implements PromiseValidator {

    @Override
    public boolean validate(Promise promise) {
        if (promise.getTitle().length() < 0 || promise.getTitle().length() > 128) {
            throw new InvalidFieldException("Title must not be empty and be longer than 128 characters!");
        }
        return true;
    }
}
