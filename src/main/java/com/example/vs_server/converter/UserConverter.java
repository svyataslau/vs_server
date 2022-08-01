package com.example.vs_server.converter;

import com.example.vs_server.model.User;
import com.example.vs_server.model.UserDto;

public class UserConverter extends Converter<UserDto, User> {

    public UserConverter() {
        super(UserConverter::convertToEntity, UserConverter::convertToDto);
    }

    private static UserDto convertToDto(User user) {
        return new UserDto(user.getNickname(), user.getEmail(), user.getPassword());
    }

    private static User convertToEntity(UserDto dto) {
        return new User(dto.getId(), dto.getNickname(), dto.getEmail(), dto.getPassword());
    }

}
