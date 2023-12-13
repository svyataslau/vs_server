package com.example.challenge.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

public interface CrudController<T> {
    List<T> getAll();

    int save(@RequestBody T promise);

    T update(@PathVariable("id") int id, @RequestBody T promise);

    int delete(@PathVariable("id") int id);
}
