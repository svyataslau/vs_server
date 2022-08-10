package com.example.vs_server.controller;

import com.example.vs_server.model.User;
import com.example.vs_server.response.ResponseFactory;
import com.example.vs_server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "User", description = "Endpoints for user entity", produces = "application/json")
@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final ResponseFactory responseFactory;

    public UserControllerImpl(UserService userService, ResponseFactory responseFactory) {
        this.userService = userService;
        this.responseFactory = responseFactory;
    }

    @ApiOperation(
            value = "Login to user profile",
            response = Object.class,
            httpMethod = "POST"
    )
    @Override
    @PostMapping("/user/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        return responseFactory.generateResponse("Successfully logged in!", HttpStatus.OK, userService.login(user));
    }

}
