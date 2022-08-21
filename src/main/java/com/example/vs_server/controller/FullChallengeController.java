package com.example.vs_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface FullChallengeController {
    @GetMapping("/full_challenges")
    ResponseEntity<Object> getAllFullChallenges();

    @GetMapping("/full_challenges/{id}")
    ResponseEntity<Object> getAllFullChallengesById(@PathVariable("id") long id);
}
