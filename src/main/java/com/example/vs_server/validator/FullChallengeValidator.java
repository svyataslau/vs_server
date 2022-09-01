package com.example.vs_server.validator;

import com.example.vs_server.model.FullChallenge;

public interface FullChallengeValidator extends Validator<FullChallenge> {
    boolean isValidTitle(String title);

    boolean validate(FullChallenge fullChallenge);
}
