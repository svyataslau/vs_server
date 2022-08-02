package com.example.vs_server.repository;

import com.example.vs_server.model.UserDto;

public interface UserDaoInterface {
    UserDto getUser(UserDto userDto);
}
