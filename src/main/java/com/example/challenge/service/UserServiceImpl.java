package com.example.challenge.service;

import com.example.challenge.converter.FullChallengeConverter;
import com.example.challenge.converter.UserConverter;
import com.example.challenge.exception.CustomServerException;
import com.example.challenge.exception.ResourceNotFoundException;
import com.example.challenge.exception.UnauthorizedException;
import com.example.challenge.model.User;
import com.example.challenge.model.UserDto;
import com.example.challenge.repository.FullChallengeDao;
import com.example.challenge.repository.UserDao;
import com.example.challenge.validator.UserValidator;
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

    @Override
    public User login(User user) {
        if (userValidator.validateEmailPassword(user)) {
            try {
                UserDto userDto = userConverter.convertToDto(user);
                User resultUser = userConverter.convertToEntity(userDao.getUser(userDto));
                resultUser.setChallenges(fullChallengeConverter.convertToEntities(
                        fullChallengeDao.findAllByUserId(resultUser.getId())));
                return resultUser;
            } catch (EmptyResultDataAccessException | NullPointerException e) {
                throw new UnauthorizedException("There is no user with such email & password"
                        + " in the database. Please check the correctness of the entered data...");
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return null;
    }

    @Override
    public User create(User user) {
        if (userValidator.validate(user)) {
            try {
                UserDto userDto = userConverter.convertToDto(user);
                User resultUser = userConverter.convertToEntity(userDao.save(userDto));
                resultUser.setChallenges(fullChallengeConverter.convertToEntities(
                        fullChallengeDao.findAllByUserId(resultUser.getId())));
                return resultUser;
            } catch (DuplicateKeyException e) {
                throw new UnauthorizedException("This user already registered. Try to login...");
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userConverter.convertToEntities(userDao.findAll());
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            User user = userConverter.convertToEntity(userDao.findById(id));
            user.setChallenges(fullChallengeConverter.convertToEntities(
                    fullChallengeDao.findAllByUserId(id)));
            user.setChallenges(fullChallengeConverter.convertToEntities(
                    fullChallengeDao.findAllByUserId(user.getId())));
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }
}
