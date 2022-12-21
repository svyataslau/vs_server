package com.example.vs_server.controller;

import com.example.vs_server.model.FullChallenge;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface FullChallengeController {
    @GetMapping("/challenges")
    List<FullChallenge> getAllFullChallenges();

    @PostMapping("/challenges")
    int save(@RequestBody FullChallenge fullChallenge);

    @PutMapping("/challenges/{id}")
    FullChallenge update(@PathVariable("id") long id, @RequestBody FullChallenge fullChallenge);

    @DeleteMapping("/challenges/{id}")
    int delete(@PathVariable("id") long id);
}
