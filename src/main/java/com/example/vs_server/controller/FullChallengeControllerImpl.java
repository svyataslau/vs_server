package com.example.vs_server.controller;

import com.example.vs_server.response.ResponseFactory;
import com.example.vs_server.service.CommonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FullChallengeControllerImpl implements FullChallengeController {

    private final CommonService fullChallengeService;
    private final ResponseFactory responseFactory;

    public FullChallengeControllerImpl(CommonService fullChallengeService, ResponseFactory responseFactory) {
        this.fullChallengeService = fullChallengeService;
        this.responseFactory = responseFactory;
    }

    @Override
    public ResponseEntity<Object> getAllFullChallenges() {
        return responseFactory.generateResponse("All full challenges received.", HttpStatus.OK, fullChallengeService.getAll());
    }

    @Override
    public ResponseEntity<Object> getAllFullChallengesById(@PathVariable("id") long id) {
        return responseFactory.generateResponse("Full challenges of user received.", HttpStatus.OK, fullChallengeService.getAllById(id));
    }

}
