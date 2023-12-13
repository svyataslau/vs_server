package com.example.challenge.service;

import java.util.List;

public interface CommonService<T> {
    List<T> getAll();

    int create(T t);

    T updateById(int id, T t);

    int deleteById(Integer id);
}
