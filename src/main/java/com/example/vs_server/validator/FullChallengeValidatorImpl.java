package com.example.vs_server.validator;

import com.example.vs_server.model.FullChallenge;
import org.springframework.stereotype.Component;

@Component
public class FullChallengeValidatorImpl implements Validator<FullChallenge>, FullChallengeValidator {

    @Override
    public boolean validate(FullChallenge fullChallenge) {
        return true;
    }
}
