package com.example.vs_server.validator;

import com.example.vs_server.exception.InvalidFieldException;
import com.example.vs_server.model.FullChallenge;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FullChallengeValidatorImpl implements FullChallengeValidator {

    @Override
    public boolean validate(FullChallenge fullChallenge) {
        if (fullChallenge.getDescription().length() < 0 || fullChallenge.getDescription().length() > 128) {
            throw new InvalidFieldException("Description must not be empty and be longer than 128 characters!");
        }
        if (fullChallenge.getTitle().length() < 0 || fullChallenge.getTitle().length() > 128) {
            throw new InvalidFieldException("Title must not be empty and be longer than 128 characters!");
        }
        if (fullChallenge.getDaysNumber() < 0 || fullChallenge.getDaysNumber() > 100000) {
            throw new InvalidFieldException("The number of days to complete the challenge must be more than 0 and not more than 10000!");
        }
        if (Objects.isNull(fullChallenge.getStartDate())) {
            throw new InvalidFieldException("Start date cannot be empty!");
        }
        if (Objects.isNull(fullChallenge.getUserId())) {
            throw new InvalidFieldException("User id cannot be empty!");
        }
        if (Objects.isNull(fullChallenge.getPromiseId())) {
            throw new InvalidFieldException("Promise id cannot be empty!");
        }
        return true;
    }
}
