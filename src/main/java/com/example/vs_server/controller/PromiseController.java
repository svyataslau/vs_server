package com.example.vs_server.controller;

import com.example.vs_server.model.Promise;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PromiseController {

    @GetMapping("/promises")
    ResponseEntity<Object> getAllPromises();

    @PostMapping("/promises")
    ResponseEntity<Object> save(@RequestBody Promise promise);

    @PutMapping("/promises/{id}")
    ResponseEntity<Object> update(@PathVariable("id") long id, @RequestBody Promise promise);

    @DeleteMapping("/promises/{id}")
    ResponseEntity<Object> delete(@PathVariable("id") long id);
}
