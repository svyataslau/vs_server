package com.example.challenge.converter;

import com.example.challenge.model.User;
import com.example.challenge.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserConverter implements Converter<UserDto, User> {
    @Override
    public List<User> convertToEntities(Collection<UserDto> userDtos) {
        return userDtos.stream().map(this::convertToEntity).toList();
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getNickname(), userDto.getEmail(), userDto.getPassword());
    }

    @Override
    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setNickname(user.getNickname());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
