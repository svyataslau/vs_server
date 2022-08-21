package com.example.vs_server.repository;

import com.example.vs_server.model.UserChallengeDto;

import java.util.List;

public interface UserChallengeDao {

    UserChallengeDto save(UserChallengeDto userChallengeDto);

    List<UserChallengeDto> findAll();
}
