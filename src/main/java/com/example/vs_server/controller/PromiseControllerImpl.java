package com.example.vs_server.controller;

import com.example.vs_server.model.Promise;
import com.example.vs_server.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@AllArgsConstructor
public class PromiseControllerImpl implements PromiseController {

    private final CommonService<Promise> promiseService;

    @Override
    public List<Promise> getAllPromises() {
        return promiseService.getAll();
    }

    @Override
    public int save(@RequestBody Promise promise) {
        return promiseService.create(promise);
    }

    @Override
    public Promise update(@PathVariable("id") long id, @RequestBody Promise promise) {
        return promiseService.updateById(id, promise);
    }

    @Override
    public int delete(long id) {
        return promiseService.deleteById(id);
    }
}

