package com.example.vs_server.service;

import com.example.vs_server.converter.FullChallengeConverter;
import com.example.vs_server.exception.CustomServerException;
import com.example.vs_server.model.FullChallenge;
import com.example.vs_server.model.FullChallengeDto;
import com.example.vs_server.repository.FullChallengeDao;
import com.example.vs_server.validator.FullChallengeValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FullChallengeServiceImpl implements CommonService<FullChallenge> {

    private final FullChallengeDao fullChallengeDao;
    private final FullChallengeConverter fullChallengeConverter;
    private final FullChallengeValidator fullChallengeValidator;

    public FullChallengeServiceImpl(FullChallengeDao fullChallengeDao, FullChallengeConverter fullChallengeConverter, FullChallengeValidator fullChallengeValidator) {
        this.fullChallengeDao = fullChallengeDao;
        this.fullChallengeConverter = fullChallengeConverter;
        this.fullChallengeValidator = fullChallengeValidator;
    }

    public List<FullChallenge> getAll() {
        try {
            return fullChallengeConverter.convertToEntities(fullChallengeDao.findAll());
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }

    public int create(FullChallenge fullChallenge) {
        if (fullChallengeValidator.validate(fullChallenge)) {
            try {
                FullChallengeDto fullChallengeDto = fullChallengeConverter.convertToDto(fullChallenge);
                int numberOfChallenges = fullChallengeDao.save(fullChallengeDto);
                return numberOfChallenges;
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return 0;
    }

    @Override
    public FullChallenge updateById(long id, FullChallenge fullChallenge) {
        if (fullChallengeValidator.validate(fullChallenge)) {
            try {
                FullChallengeDto fullChallengeDto = fullChallengeConverter.convertToDto(fullChallenge);
                FullChallenge _fullChallenge = fullChallengeConverter.convertToEntity(fullChallengeDao.updateById(id, fullChallengeDto));
                return _fullChallenge;
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return null;
    }

    @Override
    public int deleteById(Long id) {
        try {
            fullChallengeDao.deleteById(id);
        } catch (Exception e) {
            throw new CustomServerException();
        }
        return 0;
    }
}
