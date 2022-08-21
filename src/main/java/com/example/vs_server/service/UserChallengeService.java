package com.example.vs_server.service;

import com.example.vs_server.model.UserChallenge;

import java.util.List;

public interface UserChallengeService {
    UserChallenge create(UserChallenge userChallenge);

    List<UserChallenge> getAllUserChallenges();
}
