package com.example.vs_server.controller;

import com.example.vs_server.model.User;
import com.example.vs_server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "User", description = "Endpoints for user entity", produces = "application/json")
@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @ApiOperation(
            value = "Login to user profile",
            response = Object.class,
            httpMethod = "POST"
    )
    @Override
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @Override
    public User register(@RequestBody User user) {
        return userService.create(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

}
