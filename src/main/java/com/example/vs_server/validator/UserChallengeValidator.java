package com.example.vs_server.validator;

import com.example.vs_server.model.UserChallenge;

import java.sql.Timestamp;

public interface UserChallengeValidator {
    boolean isValidId(Long id);

    boolean isValidStartDate(Timestamp startDate);

    boolean validateDaysNumber(int daysNumber);

    boolean validate(UserChallenge userChallenge);
}
