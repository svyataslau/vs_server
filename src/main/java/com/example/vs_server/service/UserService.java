package com.example.vs_server.service;

import com.example.vs_server.converter.UserConverter;
import com.example.vs_server.exception.InvalidEmailException;
import com.example.vs_server.exception.InvalidPasswordException;
import com.example.vs_server.exception.UnauthorizedException;
import com.example.vs_server.model.User;
import com.example.vs_server.model.UserDto;
import com.example.vs_server.repository.UserDao;
import com.example.vs_server.validator.UserValidator;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(User user) {
        boolean isValidEmail = UserValidator.isValidEmail(user.getEmail());
        boolean isValidPassword = UserValidator.isValidPassword(user.getPassword());
        if(!isValidEmail){
            throw new InvalidEmailException("Invalid email!");
        }
        if(!isValidPassword){
            throw new InvalidPasswordException("Invalid password!");
        }
        if(isValidEmail && isValidPassword){
            UserConverter userConverter = new UserConverter();
            UserDto userDto = userConverter.convertFromEntity(user);
            try {
                return userConverter.convertFromDto(userDao.getUser(userDto));
            } catch (EmptyResultDataAccessException e) {
                throw new UnauthorizedException("There is no user with such email & password in the database. Please check the correctness of the entered data...");
            }
        }
        return null;
    }
}
