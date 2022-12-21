package com.example.vs_server.controller;

import com.example.vs_server.model.Promise;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PromiseController {

    @GetMapping("/promises")
    List<Promise> getAllPromises();

    @PostMapping("/promises")
    int save(@RequestBody Promise promise);

    @PutMapping("/promises/{id}")
    Promise update(@PathVariable("id") long id, @RequestBody Promise promise);

    @DeleteMapping("/promises/{id}")
    int delete(@PathVariable("id") long id);
}
