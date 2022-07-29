package com.example.vs_server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vs_server.model.Promise;
import com.example.vs_server.repository.PromiseRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PromiseController {

  private PromiseRepository promiseRepository;

  PromiseController(PromiseRepository promiseRepository) {
    this.promiseRepository = promiseRepository;
  }
  public PromiseRepository getPromiseRepository() {
    return promiseRepository;
  }
  @GetMapping("/promises")
  public ResponseEntity<List<Promise>> getAllPromises() {
    try {
      List<Promise> promises = new ArrayList<Promise>();

      getPromiseRepository().findAll().forEach(promises::add);

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
    Promise promise = getPromiseRepository().findById(id);

    if (promise != null) {
      return new ResponseEntity<>(promise, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/promises")
  public ResponseEntity<String> createPromise(@RequestBody Promise promise) {
    try {
      getPromiseRepository().save(new Promise(promise.getTitle()));
      return new ResponseEntity<>("Promise was created successfully.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/promises/{id}")
  public ResponseEntity<String> updatePromise(@PathVariable("id") long id, @RequestBody Promise promise) {
    Promise _promise = getPromiseRepository().findById(id);

    if (_promise != null) {
      _promise.setId(id);
      _promise.setTitle(promise.getTitle());

      getPromiseRepository().update(_promise);
      return new ResponseEntity<>("Promise was updated successfully.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Cannot find Promise with id=" + id, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/promises/{id}")
  public ResponseEntity<String> deletePromise(@PathVariable("id") long id) {
    try {
      int result = getPromiseRepository().deleteById(id);
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
      int numRows = getPromiseRepository().deleteAll();
      return new ResponseEntity<>("Deleted " + numRows + " Promise(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete promises.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
