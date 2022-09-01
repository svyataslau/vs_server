package com.example.vs_server.controller;

import com.example.vs_server.model.Promise;
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
public class PromiseControllerImpl implements PromiseController {

    private final CommonService<Promise> promiseService;
    private final ResponseFactory responseFactory;

    @Override
    public ResponseEntity<Object> getAllPromises() {
        return responseFactory.generateResponse("All promises received.", HttpStatus.OK, promiseService.getAll());
    }

    @Override
    public ResponseEntity<Object> save(@RequestBody Promise promise) {
        return responseFactory.generateResponse("Promise created.", HttpStatus.OK, promiseService.create(promise));
    }

    @Override
    public ResponseEntity<Object> update(@PathVariable("id") long id, @RequestBody Promise promise) {
        return responseFactory.generateResponse("Promise updated.", HttpStatus.OK, promiseService.updateById(id, promise));
    }

    @Override
    public ResponseEntity<Object> delete(long id) {
        return responseFactory.generateResponse("Promise deleted.", HttpStatus.OK, promiseService.deleteById(id));
    }
}

