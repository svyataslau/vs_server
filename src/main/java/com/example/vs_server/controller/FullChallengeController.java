package com.example.vs_server.controller;

import com.example.vs_server.model.FullChallenge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface FullChallengeController {
    @GetMapping("/challenges")
    ResponseEntity<Object> getAllFullChallenges();

    @PostMapping("/challenges")
    ResponseEntity<Object> save(@RequestBody FullChallenge fullChallenge);

    @PutMapping("/challenges/{id}")
    ResponseEntity<Object> update(@PathVariable("id") long id, @RequestBody FullChallenge fullChallenge);

    @DeleteMapping("/challenges/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") long id);
}
