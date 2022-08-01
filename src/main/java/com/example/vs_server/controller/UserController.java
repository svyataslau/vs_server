package com.example.vs_server.controller;

import com.example.vs_server.response.Response;
import com.example.vs_server.model.User;
import com.example.vs_server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody User user) {
      return Response.generateResponse("Successfully logged in!", HttpStatus.OK, userService.login(user));
  }

}
