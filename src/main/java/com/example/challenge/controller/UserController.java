package com.example.challenge.controller;

import com.example.challenge.model.User;
import com.example.challenge.service.UserService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@Api(tags = "User", description = "Endpoints for user entity", produces = "application/json")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

//    @ApiOperation(
//            value = "Login to user profile",
//            response = User.class,
//            httpMethod = "POST"
//    )
    @PostMapping("/users/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/users")
    public User register(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

}
