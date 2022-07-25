package com.example.vs_server.repository;

import com.example.vs_server.model.UserChallenge;

import java.util.List;

public interface UserChallengeRepository {
  int save(UserChallenge userChallenge);

  int update(UserChallenge userChallenge);

  UserChallenge findById(Long id);

  int deleteById(Long id);

  List<UserChallenge> findAll();

  int deleteAll();
}
