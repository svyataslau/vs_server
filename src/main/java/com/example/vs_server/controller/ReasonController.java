package com.example.vs_server.controller;

import com.example.vs_server.model.Reason;
import com.example.vs_server.repository.ReasonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ReasonController {

  private ReasonRepository reasonRepository;

  public ReasonController(ReasonRepository reasonRepository) {
    this.reasonRepository = reasonRepository;
  }
  @GetMapping("/reasons")
  public ResponseEntity<List<Reason>> getAllReasons() {
    try {
      List<Reason> reasons = new ArrayList<Reason>();

      reasonRepository.findAll().forEach(reasons::add);

      if (reasons.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(reasons, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/reasons/{id}")
  public ResponseEntity<Reason> getReasonById(@PathVariable("id") long id) {
    Reason reason = reasonRepository.findById(id);

    if (reason != null) {
      return new ResponseEntity<>(reason, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/reasons")
  public ResponseEntity<String> createReason(@RequestBody Reason reason) {
    try {
      reasonRepository.save(new Reason(reason.getUserChallengeId(), reason.getDescription()));
      return new ResponseEntity<>("Reason was created successfully.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/reasons/{id}")
  public ResponseEntity<String> updateReason(@PathVariable("id") long id, @RequestBody Reason reason) {
    Reason _reason = reasonRepository.findById(id);

    if (_reason != null) {
      _reason.setId(id);
      _reason.setUserChallengeId(reason.getUserChallengeId());
      _reason.setDescription(reason.getDescription());

      reasonRepository.update(_reason);
      return new ResponseEntity<>("Reason was updated successfully.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Cannot find Reason with id=" + id, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/reasons/{id}")
  public ResponseEntity<String> deleteReason(@PathVariable("id") long id) {
    try {
      int result = reasonRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find Reason with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("Reason was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete reason.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/reasons")
  public ResponseEntity<String> deleteAllReasons() {
    try {
      int numRows = reasonRepository.deleteAll();
      return new ResponseEntity<>("Deleted " + numRows + " Reason(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete reasons.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

}
