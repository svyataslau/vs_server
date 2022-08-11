package com.example.vs_server.service;

import com.example.vs_server.converter.UserConverter;
import com.example.vs_server.exception.CustomServerException;
import com.example.vs_server.exception.UnauthorizedException;
import com.example.vs_server.model.User;
import com.example.vs_server.model.UserDto;
import com.example.vs_server.repository.UserDao;
import com.example.vs_server.validator.UserValidator;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserConverter userConverter;
    private final UserValidator userValidator;

    public UserServiceImpl(UserDao userDao, UserConverter userConverter, UserValidator userValidator) {
        this.userDao = userDao;
        this.userConverter = userConverter;
        this.userValidator = userValidator;
    }

    public User login(User user) {
        if (userValidator.validateEmailPassword(user)) {
            try {
                UserDto userDto = userConverter.convertFromEntity(user);
                return userConverter.convertFromDto(userDao.getUser(userDto));
            } catch (EmptyResultDataAccessException e) {
                throw new UnauthorizedException("There is no user with such email & password in the database. Please check the correctness of the entered data...");
            } catch (NullPointerException e) {
                throw new UnauthorizedException("There is no user with such email & password in the database. Please check the correctness of the entered data...");
            } catch (Exception e) {
                System.out.println(e);
                throw new CustomServerException("Something has gone wrong...");
            }
        }
        return null;
    }

    public User create(User user) {
        if (userValidator.validate(user)) {
            try {
                UserDto userDto = userConverter.convertFromEntity(user);
                return userConverter.convertFromDto(userDao.save(userDto));
            } catch (DuplicateKeyException e) {
                throw new UnauthorizedException("This user already registered. Try to login...");
            } catch (Exception e) {
                throw new CustomServerException("Something has gone wrong...");
            }
        }
        return null;
    }
}
