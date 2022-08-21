package com.example.vs_server.controller;

import com.example.vs_server.model.UserChallenge;
import com.example.vs_server.response.ResponseFactory;
import com.example.vs_server.service.UserChallengeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserChallengeControllerImpl implements UserChallengeController {

    private final UserChallengeService userChallengeService;
    private final ResponseFactory responseFactory;

    public UserChallengeControllerImpl(UserChallengeService userChallengeService, ResponseFactory responseFactory) {
        this.userChallengeService = userChallengeService;
        this.responseFactory = responseFactory;
    }

    @Override
    public ResponseEntity<Object> save(@RequestBody UserChallenge userChallenge) {
        return responseFactory.generateResponse("User challenge successfully created!", HttpStatus.OK, userChallengeService.create(userChallenge));
    }

    @Override
    public ResponseEntity<Object> getAllUserChallenges() {
        return responseFactory.generateResponse("All user challenges received.", HttpStatus.OK, userChallengeService.getAllUserChallenges());
    }

}
