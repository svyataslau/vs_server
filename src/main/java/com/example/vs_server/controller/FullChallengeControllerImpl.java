package com.example.vs_server.controller;

import com.example.vs_server.model.FullChallenge;
import com.example.vs_server.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
public class FullChallengeControllerImpl implements FullChallengeController {

    private final CommonService<FullChallenge> fullChallengeService;

    @Override
    public List<FullChallenge> getAllFullChallenges() {
        return fullChallengeService.getAll();
    }

    @Override
    public int save(@RequestBody FullChallenge fullChallenge) {
        return fullChallengeService.create(fullChallenge);
    }

    @Override
    public FullChallenge update(@PathVariable("id") long id, @RequestBody FullChallenge fullChallenge) {
        return fullChallengeService.updateById(id, fullChallenge);
    }

    @Override
    public int delete(long id) {
        return fullChallengeService.deleteById(id);
    }

}
