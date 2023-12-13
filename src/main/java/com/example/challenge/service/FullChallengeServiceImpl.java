package com.example.challenge.service;

import com.example.challenge.converter.FullChallengeConverter;
import com.example.challenge.exception.CustomServerException;
import com.example.challenge.model.FullChallenge;
import com.example.challenge.model.FullChallengeDto;
import com.example.challenge.repository.ModelDao;
import com.example.challenge.validator.FullChallengeValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FullChallengeServiceImpl implements CommonService<FullChallenge> {

    private final ModelDao<FullChallengeDto> fullChallengeDao;
    private final FullChallengeConverter fullChallengeConverter;
    private final FullChallengeValidator fullChallengeValidator;

    @Override
    public List<FullChallenge> getAll() {
        try {
            return fullChallengeConverter.convertToEntities(fullChallengeDao.findAll());
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }

    @Override
    public int create(FullChallenge fullChallenge) {
        if (fullChallengeValidator.validate(fullChallenge)) {
            try {
                FullChallengeDto fullChallengeDto = fullChallengeConverter.convertToDto(fullChallenge);
                return fullChallengeDao.save(fullChallengeDto);
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return 0;
    }

    @Override
    public FullChallenge updateById(int id, FullChallenge fullChallenge) {
        if (fullChallengeValidator.validate(fullChallenge)) {
            try {
                FullChallengeDto fullChallengeDto = fullChallengeConverter.convertToDto(fullChallenge);
                FullChallengeDto fullChallengeDtoFromDb = fullChallengeDao.updateById(id, fullChallengeDto);
                return fullChallengeConverter.convertToEntity(fullChallengeDtoFromDb);
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return null;
    }

    @Override
    public int deleteById(Integer id) {
        try {
            fullChallengeDao.deleteById(id);
        } catch (Exception e) {
            throw new CustomServerException();
        }
        return 0;
    }
}
