package com.example.vs_server.controller;

import com.example.vs_server.model.User;
import com.example.vs_server.response.ResponseFactoryImpl;
import com.example.vs_server.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "/", description = "Endpoints for user entity", produces = "application/json")
@RestController
@RequestMapping("")
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;
    private final ResponseFactoryImpl responseFactory;

    public UserControllerImpl(UserServiceImpl userService, ResponseFactoryImpl responseFactory) {
        this.userService = userService;
        this.responseFactory = responseFactory;
    }

    @ApiOperation(value = "Login to user profile", response = Object.class, tags = "loginUser", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!")})
    @Override
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        return responseFactory.generateResponse("Successfully logged in!", HttpStatus.OK, userService.login(user));
    }

}
