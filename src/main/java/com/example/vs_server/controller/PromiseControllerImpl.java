package com.example.vs_server.controller;

import com.example.vs_server.response.ResponseFactory;
import com.example.vs_server.service.PromiseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
public class PromiseControllerImpl implements PromiseController {

    private final PromiseService promiseService;
    private final ResponseFactory responseFactory;

    @Override
    public ResponseEntity<Object> getAllPromises() {
        return responseFactory.generateResponse("All promises received.", HttpStatus.OK, promiseService.getAllPromises());
    }

}

