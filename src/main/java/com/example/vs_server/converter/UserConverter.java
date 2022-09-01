package com.example.vs_server.converter;

import com.example.vs_server.model.User;
import com.example.vs_server.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<UserDto, User> {
    @Override
    public List<User> convertToEntities(Collection<UserDto> userDtos) {
        return userDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getNickname(), userDto.getEmail(), userDto.getPassword());
    }

    @Override
    public UserDto convertToDto(User user) {
        return new UserDto(user.getNickname(), user.getEmail(), user.getPassword());
    }
}
