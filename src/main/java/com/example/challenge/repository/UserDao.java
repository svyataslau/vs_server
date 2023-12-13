package com.example.challenge.repository;

import com.example.challenge.model.UserDto;

import java.util.List;

public interface UserDao {
    UserDto getUser(UserDto userDto);

    UserDto save(UserDto userDto);

    List<UserDto> findAll();

    UserDto findById(int id);
}
