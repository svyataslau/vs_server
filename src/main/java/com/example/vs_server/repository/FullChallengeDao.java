package com.example.vs_server.repository;

import com.example.vs_server.model.FullChallengeDto;

import java.util.List;

public interface FullChallengeDao {
    List<FullChallengeDto> findAll();

    List<FullChallengeDto> findAllById(long id);

    int save(FullChallengeDto fullChallengeDto);

    FullChallengeDto updateById(long id, FullChallengeDto fullChallengeDto);

    int deleteById(long id);
}
