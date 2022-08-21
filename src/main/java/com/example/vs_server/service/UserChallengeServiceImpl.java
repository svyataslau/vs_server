package com.example.vs_server.service;

import com.example.vs_server.converter.UserChallengeConverter;
import com.example.vs_server.exception.CustomServerException;
import com.example.vs_server.exception.UnauthorizedException;
import com.example.vs_server.model.UserChallenge;
import com.example.vs_server.model.UserChallengeDto;
import com.example.vs_server.repository.UserChallengeDao;
import com.example.vs_server.validator.UserChallengeValidator;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChallengeServiceImpl implements UserChallengeService {

    private final UserChallengeDao userChallengeDao;
    private final UserChallengeConverter userChallengeConverter;
    private final UserChallengeValidator userChallengeValidator;

    public UserChallengeServiceImpl(UserChallengeDao userChallengeDao, UserChallengeConverter userChallengeConverter, UserChallengeValidator userChallengeValidator) {
        this.userChallengeDao = userChallengeDao;
        this.userChallengeConverter = userChallengeConverter;
        this.userChallengeValidator = userChallengeValidator;
    }

    public UserChallenge create(UserChallenge userChallenge) {
        if (userChallengeValidator.validate(userChallenge)) {
            try {
                UserChallengeDto userChallengeDto = userChallengeConverter.convertToDto(userChallenge);
                return userChallengeConverter.convertToEntity(userChallengeDao.save(userChallengeDto));
            } catch (DuplicateKeyException e) {
                throw new UnauthorizedException("This user already registered. Try to login...");
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return null;
    }

    public List<UserChallenge> getAllUserChallenges() {
        try {
            return userChallengeConverter.convertToEntities(userChallengeDao.findAll());
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }
}
