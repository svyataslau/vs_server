package com.example.vs_server.controller;

import com.example.vs_server.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface UserController {
    @PostMapping("/users/login")
    User login(User user);

    @PostMapping( "/users")
    User register(User user);

    @GetMapping("/users")
    List<User> getAllUsers();

    @GetMapping(path = "/users/{id}")
    User getUserById(@PathVariable("id") long id);
}
