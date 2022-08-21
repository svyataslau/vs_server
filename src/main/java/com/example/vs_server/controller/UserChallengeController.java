package com.example.vs_server.controller;

import com.example.vs_server.model.UserChallenge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserChallengeController {

    @PostMapping("/user_challenges")
    ResponseEntity<Object> save(UserChallenge userChallenge);

    @GetMapping("/user_challenges")
    ResponseEntity<Object> getAllUserChallenges();
}
