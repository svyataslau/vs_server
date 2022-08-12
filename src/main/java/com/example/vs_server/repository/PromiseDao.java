package com.example.vs_server.repository;

import com.example.vs_server.model.PromiseDto;

import java.util.List;

public interface PromiseDao {
    List<PromiseDto> findAll();
}
