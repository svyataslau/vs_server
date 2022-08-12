package com.example.vs_server.service;

import com.example.vs_server.model.User;

import java.util.List;

public interface UserService {
    User login(User user);

    User create(User user);

    List<User> getAllUsers();
}
