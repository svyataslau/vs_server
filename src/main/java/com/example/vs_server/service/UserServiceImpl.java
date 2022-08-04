package com.example.vs_server.service;

import com.example.vs_server.converter.UserConverter;
import com.example.vs_server.exception.CustomServerException;
import com.example.vs_server.exception.UnauthorizedException;
import com.example.vs_server.model.User;
import com.example.vs_server.model.UserDto;
import com.example.vs_server.repository.UserDaoImpl;
import com.example.vs_server.validator.UserValidatorImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;
    private final UserConverter userConverter;
    private final UserValidatorImpl userValidator;

    public UserServiceImpl(UserDaoImpl userDao, UserConverter userConverter, UserValidatorImpl userValidator) {
        this.userDao = userDao;
        this.userConverter = userConverter;
        this.userValidator = userValidator;
    }

    public User login(User user) {
        try {
            if (userValidator.validateEmailPassword(user)) {
                UserDto userDto = userConverter.convertFromEntity(user);
                try {
                    return userConverter.convertFromDto(userDao.getUser(userDto));
                } catch (EmptyResultDataAccessException e) {
                    throw new UnauthorizedException("There is no user with such email & password in the database. Please check the correctness of the entered data...");
                } catch (NullPointerException e) {
                    throw new UnauthorizedException("There is no user with such email & password in the database. Please check the correctness of the entered data...");
                }
            }
        } catch (Exception e) {
            throw new CustomServerException("Something has gone wrong...");
        }
        return null;
    }
}
