package com.example.vs_server.controller;

import com.example.vs_server.model.User;
import com.example.vs_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    try {
      List<User> users = new ArrayList<User>();

      userRepository.findAll().forEach(users::add);

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    User user = userRepository.findById(id);

    if (user != null) {
      return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/users")
  public ResponseEntity<String> createUser(@RequestBody User user) {
    try {
      userRepository.save(new User(user.getNickname(), user.getEmail()));
      return new ResponseEntity<>("User was created successfully.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<String> updateUser(@PathVariable("id") long id, @RequestBody User user) {
    User _user = userRepository.findById(id);

    if (_user != null) {
      _user.setId(id);
      _user.setNickname(user.getNickname());
      _user.setEmail(user.getEmail());

      userRepository.update(_user);
      return new ResponseEntity<>("User was updated successfully.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
    try {
      int result = userRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find User with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("User was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete user.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/users")
  public ResponseEntity<String> deleteAllUsers() {
    try {
      int numRows = userRepository.deleteAll();
      return new ResponseEntity<>("Deleted " + numRows + " User(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete users.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/users/published")
  public ResponseEntity<List<User>> findByPublished() {
    try {
      List<User> users = userRepository.findByPublished(true);

      if (users.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(users, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
