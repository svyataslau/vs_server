package com.example.vs_server.validator;

import com.example.vs_server.model.UserChallenge;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class UserChallengeValidatorImpl implements Validator<UserChallenge>, UserChallengeValidator {

    @Override
    public boolean isValidId(Long id) {
        return true;
    }

    @Override
    public boolean isValidStartDate(Timestamp startDate) {
        return true;
    }

    @Override
    public boolean validateDaysNumber(int daysNumber) {
        return true;
    }

    @Override
    public boolean validate(UserChallenge userChallenge) {
        return true;
    }


}
