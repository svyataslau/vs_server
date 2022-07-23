package com.example.vs_server.repository;

import com.example.vs_server.model.User;

import java.util.List;

public interface UserRepository {
  int save(User user);

  int update(User user);

  User findById(Long id);

  int deleteById(Long id);

  List<User> findAll();

  List<User> findByPublished(boolean published);

  List<User> findByNicknameContaining(String nickname);

  int deleteAll();
}
