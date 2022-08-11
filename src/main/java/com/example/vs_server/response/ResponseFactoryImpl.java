package com.example.vs_server.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseFactoryImpl implements ResponseFactory<Object, String> {

    @Override
    public ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("data", responseObj);
        return new ResponseEntity<>(map, status);
    }

    @Override
    public ResponseEntity<String> generateResponse(String message, HttpStatus status) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>("{ \"message\": \"" + message + "\"}", httpHeaders, status);
    }
}
