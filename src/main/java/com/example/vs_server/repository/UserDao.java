package com.example.vs_server.repository;

import com.example.vs_server.model.UserDto;

import java.util.List;

public interface UserDao {
    UserDto getUser(UserDto userDto);

    UserDto save(UserDto userDto);

    List<UserDto> findAll();

    UserDto findById(long id);
}
