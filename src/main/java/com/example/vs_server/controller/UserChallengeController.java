package com.example.vs_server.controller;

import com.example.vs_server.model.User;
import com.example.vs_server.model.UserChallenge;
import com.example.vs_server.repository.UserChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/api")
public class UserChallengeController {

  @Autowired
  UserChallengeRepository userChallengeRepository;

  @GetMapping("/userChallenges")
  public ResponseEntity<List<UserChallenge>> getAllUserChallenges() {
    try {
      List<UserChallenge> userChallenges = new ArrayList<UserChallenge>();

      userChallengeRepository.findAll().forEach(userChallenges::add);

      if (userChallenges.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(userChallenges, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PostMapping("/userChallenges")
  public ResponseEntity<String> createUserChallenge(@RequestBody UserChallenge userChallenge) {
    try {
      userChallengeRepository.save(new UserChallenge(userChallenge.getUserId(), userChallenge.getPromiseId(), userChallenge.getDescription(), userChallenge.getStartDate(), userChallenge.getDaysNumber()));
      return new ResponseEntity<>("UserChallenge was created successfully.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/userChallenges/{id}")
  public ResponseEntity<UserChallenge> getUserChallengeById(@PathVariable("id") long id) {
    UserChallenge userChallenge = userChallengeRepository.findById(id);

    if (userChallenge != null) {
      return new ResponseEntity<>(userChallenge, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @PutMapping("/userChallenges/{id}")
  public ResponseEntity<String> updateUserChallenge(@PathVariable("id") long id, @RequestBody UserChallenge userChallenge) {
    UserChallenge _userChallenge = userChallengeRepository.findById(id);

    if (_userChallenge != null) {
      _userChallenge.setUserId(userChallenge.getUserId());
      _userChallenge.setPromiseId(userChallenge.getPromiseId());
      _userChallenge.setDescription(userChallenge.getDescription());
      _userChallenge.setStartDate(userChallenge.getStartDate());
      _userChallenge.setDaysNumber(userChallenge.getDaysNumber());

      userChallengeRepository.update(_userChallenge);
      return new ResponseEntity<>("UserChallenge was updated successfully.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Cannot find UserChallenge with id=" + id, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/userChallenges/{id}")
  public ResponseEntity<String> deleteUserChallenge(@PathVariable("id") long id) {
    try {
      int result = userChallengeRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find UserChallenge with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("UserChallenge was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete userChallenge.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/userChallenges")
  public ResponseEntity<String> deleteAllUsers() {
    try {
      int numRows = userChallengeRepository.deleteAll();
      return new ResponseEntity<>("Deleted " + numRows + " UserChallenge(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete userChallenges.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
