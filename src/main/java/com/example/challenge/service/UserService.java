package com.example.challenge.service;

import com.example.challenge.model.User;

import java.util.List;

public interface UserService {
    User login(User user);

    User create(User user);

    List<User> getAllUsers();

    User getUserById(int id);
}
