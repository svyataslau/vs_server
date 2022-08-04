package com.example.vs_server.controller;

import com.example.vs_server.response.ResponseFactoryImpl;
import com.example.vs_server.model.User;
import com.example.vs_server.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserControllerImpl implements UserController {

  private final UserServiceImpl userService;
  private final ResponseFactoryImpl responseFactory;

  public UserControllerImpl(UserServiceImpl userService, ResponseFactoryImpl responseFactory) {
    this.userService = userService;
    this.responseFactory = responseFactory;
  }

  @Override
  public ResponseEntity<Object> login(@RequestBody User user) {
      return responseFactory.generateResponse("Successfully logged in!", HttpStatus.OK, userService.login(user));
  }

}
