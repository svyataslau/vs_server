package com.example.vs_server.service;

import com.example.vs_server.model.User;

public interface UserService {
    User login(User user);

    User create(User user);
}
