package com.example.vs_server.service;

import java.util.List;

public interface CommonService<T> {
    List<T> getAll();

    int create(T t);

    T updateById(long id, T t);

    int deleteById(Long id);
}
