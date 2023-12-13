package com.example.challenge.controller;

import com.example.challenge.model.User;
import com.example.challenge.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserControllerTest
{
    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;
    public final User user = new User(null,null,null,null);

    @Test
    void shouldCallUserServiceLoginMethod()
    {
        userController.login(user);
        verify(userService, times(1)).login(user);
    }

    @Test
    void shouldCallUserServiceCreateMethod()
    {
        userController.register(user);
        verify(userService, times(1)).create(user);
    }

    @Test
    void shouldCallUserServiceGetAllUsersMethod()
    {
        userController.getAllUsers();
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void shouldCallUserServiceGetUserByIdMethod()
    {
        int userId = anyInt();
        userController.getUserById(userId);
        verify(userService, times(1)).getUserById(userId);
    }

}
