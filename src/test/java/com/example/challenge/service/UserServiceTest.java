package com.example.challenge.service;

import com.example.challenge.converter.FullChallengeConverter;
import com.example.challenge.converter.UserConverter;
import com.example.challenge.exception.CustomServerException;
import com.example.challenge.exception.ResourceNotFoundException;
import com.example.challenge.exception.UnauthorizedException;
import com.example.challenge.model.FullChallenge;
import com.example.challenge.model.User;
import com.example.challenge.model.UserDto;
import com.example.challenge.repository.FullChallengeDao;
import com.example.challenge.repository.UserDaoImpl;
import com.example.challenge.validator.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDaoImpl userDao;
    @Mock
    FullChallengeDao fullChallengeDao;
    @Mock
    UserConverter userConverter;
    @Mock
    UserValidator userValidator;
    @Mock
    FullChallengeConverter fullChallengeConverter;

    @Test
    void testLoginIfUserExists() {
        User userFromUI = new User(null, null, "helper89@yahoo.com", "somePassword8989");
        User userFromDao = new User(1, "someNickname987", "helper89@yahoo.com", "somePassword8989");
        List<FullChallenge> challenges = new ArrayList<>();
        challenges.add(new FullChallenge(1, 1, 1, null, 1, "Some title", "Some description"));

        UserDto convertedUserFromUI = new UserDto();
        convertedUserFromUI.setEmail("helper89@yahoo.com");
        convertedUserFromUI.setPassword("somePassword8989");

        when(userValidator.validateEmailPassword(userFromUI)).thenReturn(true);
        when(userConverter.convertToDto(userFromUI)).thenReturn(convertedUserFromUI);
        when(userConverter.convertToEntity(userDao.getUser(convertedUserFromUI))).thenReturn(userFromDao);
        when(fullChallengeConverter.convertToEntities(fullChallengeDao.findAllByUserId(userFromDao.getId()))).thenReturn(challenges);

        User response = userService.login(userFromUI);

        assertNotNull(response);

        assertEquals(response.getId(), userFromDao.getId());
        assertEquals(response.getNickname(), userFromDao.getNickname());
        assertEquals(response.getEmail(), userFromDao.getEmail());
        assertEquals(response.getPassword(), userFromDao.getPassword());
        assertEquals(response.getChallenges(), challenges);
    }

    @Test
    void testLoginIfUserUnregisteredMustThrowUnauthorizedException() {
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            User userFromUI = new User(null, null, "helper89@yahoo.com", "somePassword8989");

            UserDto convertedUserFromUI = new UserDto();
            convertedUserFromUI.setEmail("helper89@yahoo.com");
            convertedUserFromUI.setPassword("somePassword8989");

            when(userValidator.validateEmailPassword(userFromUI)).thenReturn(true);
            when(userConverter.convertToDto(userFromUI)).thenReturn(convertedUserFromUI);
            when(userConverter.convertToEntity(userDao.getUser(convertedUserFromUI)))
                    .thenThrow(new EmptyResultDataAccessException(anyInt()));

            userService.login(userFromUI);
        });
    }

    @Test
    void testLoginIfUserInvalidMustReturnNull() {
        User userFromUI = new User(null, null, null, null);
        when(userValidator.validateEmailPassword(userFromUI)).thenReturn(false);

        User user = userService.login(userFromUI);
        assertThat(user).isNull();
    }

    @Test
    void testLoginIfReferenceToNullMustThrowUnauthorizedException() {
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            User userFromUI = new User(null, null, "helper89@yahoo.com", "somePassword8989");

            UserDto convertedUserFromUI = new UserDto();
            convertedUserFromUI.setEmail("helper89@yahoo.com");
            convertedUserFromUI.setPassword("somePassword8989");

            when(userValidator.validateEmailPassword(userFromUI)).thenReturn(true);
            when(userConverter.convertToDto(userFromUI)).thenReturn(convertedUserFromUI);
            when(userConverter.convertToEntity(userDao.getUser(convertedUserFromUI))).thenReturn(null);

            userService.login(userFromUI);
        });
    }

    @Test
    void testLoginIfUnexpectedExceptionOccursMustThrowCustomServerException() {
        Assertions.assertThrows(CustomServerException.class, () -> {
            User userFromUI = new User(null, null, "helper89@yahoo.com", "somePassword8989");

            when(userValidator.validateEmailPassword(userFromUI)).thenReturn(true);
            when(userConverter.convertToDto(userFromUI)).thenThrow(RuntimeException.class);

            userService.login(userFromUI);
        });
    }

    @Test
    void testCreateIfNoSuchUserInDB() {

        User userFromUI = new User(null, "someNickname987", "helper89@yahoo.com", "somePassword8989");
        User userFromDao = new User(1, "someNickname987", "helper89@yahoo.com", "somePassword8989");
        List<FullChallenge> challenges = new ArrayList<>();

        UserDto convertedUserFromUI = new UserDto();
        convertedUserFromUI.setNickname("someNickname987");
        convertedUserFromUI.setEmail("helper89@yahoo.com");
        convertedUserFromUI.setPassword("somePassword8989");

        when(userValidator.validate(userFromUI)).thenReturn(true);
        when(userConverter.convertToDto(userFromUI)).thenReturn(convertedUserFromUI);
        when(userConverter.convertToEntity(userDao.save(convertedUserFromUI))).thenReturn(userFromDao);
        when(fullChallengeConverter.convertToEntities(fullChallengeDao.findAllByUserId(userFromDao.getId()))).thenReturn(challenges);

        User response = userService.create(userFromUI);

        assertNotNull(response);

        assertEquals(response.getId(), userFromDao.getId());
        assertEquals(response.getNickname(), userFromDao.getNickname());
        assertEquals(response.getEmail(), userFromDao.getEmail());
        assertEquals(response.getPassword(), userFromDao.getPassword());
        assertEquals(response.getChallenges(), challenges);

    }

    @Test
    void testCreateIfUserWithSuchRegisteredMustThrowUnauthorizedException() {
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            User userFromUI = new User(null, "someNickname987", "helper89@yahoo.com", "somePassword8989");

            UserDto convertedUserFromUI = new UserDto();
            convertedUserFromUI.setNickname("someNickname987");
            convertedUserFromUI.setEmail("helper89@yahoo.com");
            convertedUserFromUI.setPassword("somePassword8989");

            when(userValidator.validate(userFromUI)).thenReturn(true);
            when(userConverter.convertToDto(userFromUI)).thenReturn(convertedUserFromUI);
            when(userConverter.convertToEntity(userDao.save(convertedUserFromUI))).thenThrow(new DuplicateKeyException("Duplicate key"));

            userService.create(userFromUI);
        });
    }

    @Test
    void testCreateIfUserInvalidMustReturnNull() {
        User userFromUI = new User(null, null, null, null);
        when(userValidator.validate(userFromUI)).thenReturn(false);

        User user = userService.create(userFromUI);
        assertThat(user).isNull();
    }

    @Test
    void testCreateIfUnexpectedExceptionOccursMustThrowCustomServerException() {
        Assertions.assertThrows(CustomServerException.class, () -> {
            User userFromUI = new User(null, null, "helper89@yahoo.com", "somePassword8989");

            when(userValidator.validate(userFromUI)).thenReturn(true);
            when(userConverter.convertToDto(userFromUI)).thenThrow(RuntimeException.class);

            userService.create(userFromUI);
        });
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1234, "helpUs8989", "helper89@yahoo.com", "somePassword8989");
        User user2 = new User(1235, "helpUs7171", "helper71@yahoo.com", "somePassword7171");

        List<User> userList = new ArrayList<>(
                Arrays.asList(user1,
                        user2)
        );

        when(userConverter.convertToEntities(userDao.findAll())).thenReturn(userList);

        List<User> response = userService.getAllUsers();

        assertThat(response).hasSize(2);
        assertEquals(response.get(0), userList.get(0));
        assertEquals(response.get(1), userList.get(1));
    }

    @Test
    void testGetAllUsersWithUnexpectedException() {
        Assertions.assertThrows(CustomServerException.class, () -> {
            when(userConverter.convertToEntities(userDao.findAll())).thenThrow(RuntimeException.class);
            userService.getAllUsers();
        });
    }

    @Test
    void testGetUserById() {
        User user = new User(1234, "helpUs8989", "helper89@yahoo.com", "somePassword8989");

        when(userConverter.convertToEntity(userDao.findById(user.getId()))).thenReturn(user);

        User response = userService.getUserById(1234);

        assertNotNull(response);
        assertEquals(1234, response.getId());
        assertEquals(user.getNickname(), response.getNickname());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getPassword(), response.getPassword());
    }

    @Test
    void testGetUserByIdIfSuchUserDoesNotExistMustThrowResourceNotFoundException() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            when(userConverter.convertToEntity(
                    userDao.findById(anyInt()))
            ).thenThrow(EmptyResultDataAccessException.class);
            userService.getUserById(anyInt());
        });
    }

    @Test
    void testGetUserByIdWithUnexpectedException() {
        Assertions.assertThrows(CustomServerException.class, () -> {
            when(userConverter.convertToEntity(
                    userDao.findById(anyInt()))
            ).thenThrow(RuntimeException.class);
            userService.getUserById(anyInt());
        });
    }

}
