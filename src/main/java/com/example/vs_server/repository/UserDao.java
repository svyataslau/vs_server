package com.example.vs_server.repository;

import com.example.vs_server.model.UserDto;

public interface UserDao {
    UserDto getUser(UserDto userDto);

    UserDto save(UserDto userDto);
}
