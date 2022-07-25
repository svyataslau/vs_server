package com.example.vs_server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vs_server.model.Promise;
import com.example.vs_server.repository.PromiseRepository;

@RestController
@RequestMapping("/api")
public class PromiseController {

  @Autowired
  PromiseRepository promiseRepository;

  @GetMapping("/promises")
  public ResponseEntity<List<Promise>> getAllPromises() {
    try {
      List<Promise> promises = new ArrayList<Promise>();

      promiseRepository.findAll().forEach(promises::add);

      if (promises.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(promises, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/promises/{id}")
  public ResponseEntity<Promise> getPromiseById(@PathVariable("id") long id) {
    Promise promise = promiseRepository.findById(id);

    if (promise != null) {
      return new ResponseEntity<>(promise, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/promises")
  public ResponseEntity<String> createPromise(@RequestBody Promise promise) {
    try {
      promiseRepository.save(new Promise(promise.getTitle()));
      return new ResponseEntity<>("Promise was created successfully.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/promises/{id}")
  public ResponseEntity<String> updatePromise(@PathVariable("id") long id, @RequestBody Promise promise) {
    Promise _promise = promiseRepository.findById(id);

    if (_promise != null) {
      _promise.setId(id);
      _promise.setTitle(promise.getTitle());

      promiseRepository.update(_promise);
      return new ResponseEntity<>("Promise was updated successfully.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Cannot find Promise with id=" + id, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/promises/{id}")
  public ResponseEntity<String> deletePromise(@PathVariable("id") long id) {
    try {
      int result = promiseRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find Promise with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("Promise was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete promise.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/promises")
  public ResponseEntity<String> deleteAllPromises() {
    try {
      int numRows = promiseRepository.deleteAll();
      return new ResponseEntity<>("Deleted " + numRows + " Promise(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete promises.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
