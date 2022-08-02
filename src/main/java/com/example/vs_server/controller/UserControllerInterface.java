package com.example.vs_server.controller;

import com.example.vs_server.model.User;
import org.springframework.http.ResponseEntity;

public interface UserControllerInterface {
    ResponseEntity<Object> login(User user);
}
