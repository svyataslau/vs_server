package com.example.vs_server.service;

import com.example.vs_server.converter.FullChallengeConverter;
import com.example.vs_server.exception.CustomServerException;
import com.example.vs_server.model.FullChallenge;
import com.example.vs_server.repository.FullChallengeDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FullChallengeServiceImpl implements CommonService<FullChallenge> {

    private final FullChallengeDao fullChallengeDao;
    private final FullChallengeConverter fullChallengeConverter;

    public FullChallengeServiceImpl(FullChallengeDao fullChallengeDao, FullChallengeConverter fullChallengeConverter) {
        this.fullChallengeDao = fullChallengeDao;
        this.fullChallengeConverter = fullChallengeConverter;
    }

    public List<FullChallenge> getAll() {
        try {
            return fullChallengeConverter.convertToEntities(fullChallengeDao.findAll());
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }

    public List<FullChallenge> getAllById(long id) {
        try {
            return fullChallengeConverter.convertToEntities(fullChallengeDao.findAllById(id));
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }
}
