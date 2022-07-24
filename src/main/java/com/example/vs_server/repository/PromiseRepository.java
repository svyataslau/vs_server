package com.example.vs_server.repository;

import java.util.List;

import com.example.vs_server.model.Promise;

public interface PromiseRepository {
  int save(Promise promise);

  int update(Promise promise);

  Promise findById(Long id);

  int deleteById(Long id);

  List<Promise> findAll();

  List<Promise> findByTitleContaining(String title);

  int deleteAll();
}
