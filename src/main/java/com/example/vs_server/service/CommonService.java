package com.example.vs_server.service;

import java.util.List;

public interface CommonService<T> {
    List<T> getAll();

    List<T> getAllById(long id);
}
