package com.example.vs_server.repository;

import com.example.vs_server.model.FullChallengeDto;

import java.util.List;

public interface FullChallengeDao {
    List<FullChallengeDto> findAll();

    int save(FullChallengeDto fullChallengeDto);

    FullChallengeDto updateById(long id, FullChallengeDto fullChallengeDto);

    int deleteById(long id);

    List<FullChallengeDto> findAllByUserId(long id);
}
