package com.example.vs_server.controller;

import com.example.vs_server.model.User;
import com.example.vs_server.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest
{
    @InjectMocks
    UserControllerImpl userController;

    @Mock
    UserServiceImpl userService;
    public final User user = new User(null,null,null,null);

    @Test
    public void shouldCallUserServiceLoginMethod()
    {
        userController.login(user);
        verify(userService, times(1)).login(user);
    }

    @Test
    public void shouldCallUserServiceCreateMethod()
    {
        userController.register(user);
        verify(userService, times(1)).create(user);
    }

    @Test
    public void shouldCallUserServiceGetAllUsersMethod()
    {
        userController.getAllUsers();
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void shouldCallUserServiceGetUserByIdMethod()
    {
        long userId = anyLong();
        userController.getUserById(userId);
        verify(userService, times(1)).getUserById(userId);
    }

}
