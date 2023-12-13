package com.example.challenge.controller.impl;

import com.example.challenge.controller.CrudController;
import com.example.challenge.model.Promise;
import com.example.challenge.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/promises")
public class PromiseControllerImpl implements CrudController<Promise> {

    private final CommonService<Promise> promiseService;

    @GetMapping
    @Override
    public List<Promise> getAll() {
        return promiseService.getAll();
    }

    @PostMapping
    @Override
    public int save(@RequestBody Promise promise) {
        return promiseService.create(promise);
    }

    @PutMapping("/{id}")
    @Override
    public Promise update(@PathVariable("id") int id, @RequestBody Promise promise) {
        return promiseService.updateById(id, promise);
    }

    @DeleteMapping("/{id}")
    @Override
    public int delete(@PathVariable("id") int id) {
        return promiseService.deleteById(id);
    }
}

