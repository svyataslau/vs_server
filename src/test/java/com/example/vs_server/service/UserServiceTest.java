package com.example.vs_server.service;

import com.example.vs_server.converter.FullChallengeConverter;
import com.example.vs_server.converter.UserConverter;
import com.example.vs_server.exception.CustomServerException;
import com.example.vs_server.exception.ResourceNotFoundException;
import com.example.vs_server.exception.UnauthorizedException;
import com.example.vs_server.model.FullChallenge;
import com.example.vs_server.model.User;
import com.example.vs_server.model.UserDto;
import com.example.vs_server.repository.FullChallengeDao;
import com.example.vs_server.repository.UserDaoImpl;
import com.example.vs_server.validator.UserValidator;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

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
    public void testLoginIfUserExists()
    {
        User userFromUI = new User(null,null,"helper89@yahoo.com","somePassword8989");
        User userFromDao = new User(1l,"someNickname987","helper89@yahoo.com","somePassword8989");
        List<FullChallenge> challenges = new ArrayList<>();
        challenges.add(new FullChallenge(1l,1l, 1l,null,1,"Some title", "Some description"));

        UserDto convertedUserFromUI = new UserDto();
        convertedUserFromUI.setEmail("helper89@yahoo.com");
        convertedUserFromUI.setPassword("somePassword8989");

        when(userValidator.validateEmailPassword(userFromUI)).thenReturn(true);
        when(userConverter.convertToDto(userFromUI)).thenReturn(convertedUserFromUI);
        when(userConverter.convertToEntity(userDao.getUser(convertedUserFromUI))).thenReturn(userFromDao);
        when(fullChallengeConverter.convertToEntities(fullChallengeDao.findAllByUserId(userFromDao.getId()))).thenReturn(challenges);

        User response = userService.login(userFromUI);

        assertNotNull(response);

        assertEquals(response.getId(),userFromDao.getId());
        assertEquals(response.getNickname(), userFromDao.getNickname());
        assertEquals(response.getEmail(), userFromDao.getEmail());
        assertEquals(response.getPassword(), userFromDao.getPassword());
        assertEquals(response.getChallenges(), challenges);
    }

    @Test
    public void testLoginIfUserUnregisteredMustThrowUnauthorizedException()
    {
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            User userFromUI = new User(null,null,"helper89@yahoo.com","somePassword8989");

            UserDto convertedUserFromUI = new UserDto();
            convertedUserFromUI.setEmail("helper89@yahoo.com");
            convertedUserFromUI.setPassword("somePassword8989");

            when(userValidator.validateEmailPassword(userFromUI)).thenReturn(true);
            when(userConverter.convertToDto(userFromUI)).thenReturn(convertedUserFromUI);
            when(userConverter.convertToEntity(userDao.getUser(convertedUserFromUI))).thenThrow(new EmptyResultDataAccessException(anyInt()));

            userService.login(userFromUI);
        });
    }

    @Test
    public void testLoginIfUserInvalidMustReturnNull()
    {
        User userFromUI = new User(null,null,null,null);
        when(userValidator.validateEmailPassword(userFromUI)).thenReturn(false);

        User user = userService.login(userFromUI);
        assertEquals(user, null);
    }

    @Test
    public void testLoginIfReferenceToNullMustThrowUnauthorizedException() {
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            User userFromUI = new User(null,null,"helper89@yahoo.com","somePassword8989");

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
    public void testLoginIfUnexpectedExceptionOccursMustThrowCustomServerException() {
        Assertions.assertThrows(CustomServerException.class, () -> {
            User userFromUI = new User(null,null,"helper89@yahoo.com","somePassword8989");

            when(userValidator.validateEmailPassword(userFromUI)).thenReturn(true);
            when(userConverter.convertToDto(userFromUI)).thenThrow(RuntimeException.class);

            userService.login(userFromUI);
        });
    }

    @Test
    public void testCreateIfNoSuchUserInDB()
    {

        User userFromUI = new User(null,"someNickname987","helper89@yahoo.com","somePassword8989");
        User userFromDao = new User(1l,"someNickname987","helper89@yahoo.com","somePassword8989");
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

        assertEquals(response.getId(),userFromDao.getId());
        assertEquals(response.getNickname(), userFromDao.getNickname());
        assertEquals(response.getEmail(), userFromDao.getEmail());
        assertEquals(response.getPassword(), userFromDao.getPassword());
        assertEquals(response.getChallenges(), challenges);

    }

    @Test
    public void testCreateIfUserWithSuchRegisteredMustThrowUnauthorizedException()
    {
        Assertions.assertThrows(UnauthorizedException.class, () -> {
            User userFromUI = new User(null,"someNickname987","helper89@yahoo.com","somePassword8989");

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
    public void testCreateIfUserInvalidMustReturnNull()
    {
        User userFromUI = new User(null,null,null,null);
        when(userValidator.validate(userFromUI)).thenReturn(false);

        User user = userService.create(userFromUI);
        assertEquals(user, null);
    }

    @Test
    public void testCreateIfUnexpectedExceptionOccursMustThrowCustomServerException() {
        Assertions.assertThrows(CustomServerException.class, () -> {
            User userFromUI = new User(null,null,"helper89@yahoo.com","somePassword8989");

            when(userValidator.validate(userFromUI)).thenReturn(true);
            when(userConverter.convertToDto(userFromUI)).thenThrow(RuntimeException.class);

            userService.create(userFromUI);
        });
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User(1234l,"helpUs8989","helper89@yahoo.com","somePassword8989");
        User user2 = new User(1235l,"helpUs7171","helper71@yahoo.com","somePassword7171");

        List<User> userList = new ArrayList<>(
                Arrays.asList(user1,
                        user2)
        );

        when(userConverter.convertToEntities(userDao.findAll())).thenReturn(userList);

        List<User> response = userService.getAllUsers();

        assertThat(response.size()).isEqualTo(2);
        assertEquals(response.get(0), userList.get(0));
        assertEquals(response.get(1), userList.get(1));
    }

    @Test
    public void testGetAllUsersWithUnexpectedException() {
        Assertions.assertThrows(CustomServerException.class, () -> {
            when(userConverter.convertToEntities(userDao.findAll())).thenThrow(RuntimeException.class);
            userService.getAllUsers();
        });
    }

    @Test
    public void testGetUserById()
    {
        User user = new User(1234l,"helpUs8989","helper89@yahoo.com","somePassword8989");

        when(userConverter.convertToEntity(userDao.findById(user.getId()))).thenReturn(user);

        User response = userService.getUserById(1234l);

        assertNotNull(response);
        assertEquals(1234l, response.getId());
        assertEquals(user.getNickname(),response.getNickname());
        assertEquals(user.getEmail(),response.getEmail());
        assertEquals(user.getPassword(),response.getPassword());
    }

    @Test
    public void testGetUserByIdIfSuchUserDoesNotExistMustThrowResourceNotFoundException()
    {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            when(userConverter.convertToEntity(
                    userDao.findById(anyLong()))
            ).thenThrow(EmptyResultDataAccessException.class);
            userService.getUserById(anyLong());
        });
    }

    @Test
    public void testGetUserByIdWithUnexpectedException()
    {
        Assertions.assertThrows(CustomServerException.class, () -> {
            when(userConverter.convertToEntity(
                    userDao.findById(anyLong()))
            ).thenThrow(RuntimeException.class);
            userService.getUserById(anyLong());
        });
    }

}
