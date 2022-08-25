package com.example.vs_server.controller;

import com.example.vs_server.model.FullChallenge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface FullChallengeController {
    @GetMapping("/full_challenges")
    ResponseEntity<Object> getAllFullChallenges();

    @GetMapping("/full_challenges/{id}")
    ResponseEntity<Object> getAllFullChallengesById(@PathVariable("id") long id);

    @PostMapping("/full_challenges")
    ResponseEntity<Object> save(@RequestBody FullChallenge fullChallenge);

    @PutMapping("/full_challenges/{id}")
    ResponseEntity<Object> update(@PathVariable("id") long id, @RequestBody FullChallenge fullChallenge);

    @DeleteMapping("/full_challenges/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") long id);
}
