package com.example.vs_server.controller;

import com.example.vs_server.model.FullChallenge;
import com.example.vs_server.response.ResponseFactory;
import com.example.vs_server.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
public class FullChallengeControllerImpl implements FullChallengeController {

    private final CommonService<FullChallenge> fullChallengeService;
    private final ResponseFactory responseFactory;

    @Override
    public ResponseEntity<Object> getAllFullChallenges() {
        return responseFactory.generateResponse("All full challenges received.", HttpStatus.OK, fullChallengeService.getAll());
    }

    @Override
    public ResponseEntity<Object> save(@RequestBody FullChallenge fullChallenge) {
        return responseFactory.generateResponse("Full challenge created.", HttpStatus.OK, fullChallengeService.create(fullChallenge));
    }

    @Override
    public ResponseEntity<Object> update(@PathVariable("id") long id, @RequestBody FullChallenge fullChallenge) {
        return responseFactory.generateResponse("Full challenge updated.", HttpStatus.OK, fullChallengeService.updateById(id, fullChallenge));
    }

    @Override
    public ResponseEntity<Object> delete(long id) {
        return responseFactory.generateResponse("Full challenge deleted.", HttpStatus.OK, fullChallengeService.deleteById(id));
    }

}
