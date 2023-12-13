package com.example.challenge.validator;

import com.example.challenge.exception.InvalidFieldException;
import com.example.challenge.model.FullChallenge;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class FullChallengeValidatorImpl implements FullChallengeValidator {


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
    public boolean validate(FullChallenge fullChallenge) {
        if (!isValidTitle(fullChallenge.getTitle())) {
            throw new InvalidFieldException("Title can contain latin letters, numbers, space -_.");
        }
        if (fullChallenge.getDescription().length() < 0 || fullChallenge.getDescription().length() > 2048) {
            throw new InvalidFieldException("Description must not be empty and be longer than 2048 characters!");
        }
        if (fullChallenge.getTitle().length() < 0 || fullChallenge.getTitle().length() > 128) {
            throw new InvalidFieldException("Title must not be empty and be longer than 128 characters!");
        }
        if (fullChallenge.getDaysNumber() < 0 || fullChallenge.getDaysNumber() > 100000) {
            throw new InvalidFieldException(
                    "The number of days to complete the challenge must be more than 0 and not more than 10000!");
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
