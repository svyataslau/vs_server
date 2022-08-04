package com.example.vs_server.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseFactory<T, U> {
    ResponseEntity<T> generateResponse(String message, HttpStatus status, T responseObj);

    ResponseEntity<U> generateResponse(String message, HttpStatus status);
}
