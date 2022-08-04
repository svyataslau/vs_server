package com.example.vs_server.controller;

import com.example.vs_server.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserController {
    @PostMapping("/login")
    ResponseEntity<Object> login(User user);
}
