package com.example.vs_server.service;

import com.example.vs_server.converter.FullChallengeConverter;
import com.example.vs_server.converter.UserConverter;
import com.example.vs_server.exception.CustomServerException;
import com.example.vs_server.exception.UnauthorizedException;
import com.example.vs_server.model.User;
import com.example.vs_server.model.UserDto;
import com.example.vs_server.repository.FullChallengeDao;
import com.example.vs_server.repository.UserDao;
import com.example.vs_server.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final FullChallengeDao fullChallengeDao;
    private final UserConverter userConverter;
    private final UserValidator userValidator;
    private final FullChallengeConverter fullChallengeConverter;

    public User login(User user) {
        if (userValidator.validateEmailPassword(user)) {
            try {
                UserDto userDto = userConverter.convertToDto(user);
                User _user = userConverter.convertToEntity(userDao.getUser(userDto));
                _user.setChallenges(fullChallengeConverter.convertToEntities(fullChallengeDao.findAllByUserId(_user.getId())));
                return _user;
            } catch (EmptyResultDataAccessException e) {
                throw new UnauthorizedException("There is no user with such email & password in the database. Please check the correctness of the entered data...");
            } catch (NullPointerException e) {
                throw new UnauthorizedException("There is no user with such email & password in the database. Please check the correctness of the entered data...");
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return null;
    }

    public User create(User user) {
        if (userValidator.validate(user)) {
            try {
                UserDto userDto = userConverter.convertToDto(user);
                User _user = userConverter.convertToEntity(userDao.save(userDto));
                _user.setChallenges(fullChallengeConverter.convertToEntities(fullChallengeDao.findAllByUserId(_user.getId())));
                return _user;
            } catch (DuplicateKeyException e) {
                throw new UnauthorizedException("This user already registered. Try to login...");
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        try {
            return userConverter.convertToEntities(userDao.findAll());
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }

    public User getUserById(long id) {
        try {
            User user = userConverter.convertToEntity(userDao.findById(id));
            user.setChallenges(fullChallengeConverter.convertToEntities(fullChallengeDao.findAllByUserId(id)));
            user.setChallenges(fullChallengeConverter.convertToEntities(fullChallengeDao.findAllByUserId(user.getId())));
            return user;
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }
}
