package com.example.vs_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface PromiseController {

    @GetMapping("/promises")
    ResponseEntity<Object> getAllPromises();
}
