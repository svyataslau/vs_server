package com.example.challenge.service;

import com.example.challenge.converter.PromiseConverter;
import com.example.challenge.exception.CustomServerException;
import com.example.challenge.model.Promise;
import com.example.challenge.model.PromiseDto;
import com.example.challenge.repository.ModelDao;
import com.example.challenge.validator.PromiseValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromiseServiceImpl implements CommonService<Promise> {

    private final ModelDao<PromiseDto> promiseDao;
    private final PromiseConverter promiseConverter;
    private final PromiseValidator promiseValidator;

    @Override
    public List<Promise> getAll() {
        try {
            return promiseConverter.convertToEntities(promiseDao.findAll());
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }

    @Override
    public int create(Promise promise) {
        if (promiseValidator.validate(promise)) {
            try {
                PromiseDto promiseDto = promiseConverter.convertToDto(promise);
                int numberOfChallenges = promiseDao.save(promiseDto);
                return numberOfChallenges;
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return 0;
    }

    @Override
    public Promise updateById(int id, Promise promise) {
        if (promiseValidator.validate(promise)) {
            try {
                PromiseDto promiseDto = promiseConverter.convertToDto(promise);
                Promise resultPromise = promiseConverter.convertToEntity(promiseDao.updateById(id, promiseDto));
                return resultPromise;
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return null;
    }

    @Override
    public int deleteById(Integer id) {
        try {
            promiseDao.deleteById(id);
        } catch (Exception e) {
            throw new CustomServerException();
        }
        return 0;
    }
}
