package com.example.challenge.repository;

import java.util.List;

public interface ModelDao<T> {
    List<T> findAll();

    int save(T t);

    T updateById(int id, T t);

    int deleteById(int id);
}
