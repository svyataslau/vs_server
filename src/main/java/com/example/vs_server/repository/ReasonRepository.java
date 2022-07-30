package com.example.vs_server.repository;

import com.example.vs_server.model.Reason;

import java.util.List;

public interface ReasonRepository {
  int save(Reason reason);

  int update(Reason reason);

  Reason findById(Long id);

  int deleteById(Long id);

  List<Reason> findAll();

  int deleteAll();
}
