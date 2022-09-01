package com.example.vs_server.service;

import com.example.vs_server.converter.PromiseConverter;
import com.example.vs_server.exception.CustomServerException;
import com.example.vs_server.model.Promise;
import com.example.vs_server.model.PromiseDto;
import com.example.vs_server.repository.PromiseDao;
import com.example.vs_server.validator.PromiseValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromiseServiceImpl implements CommonService<Promise> {

    private final PromiseDao promiseDao;
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
    public Promise updateById(long id, Promise promise) {
        if (promiseValidator.validate(promise)) {
            try {
                PromiseDto promiseDto = promiseConverter.convertToDto(promise);
                Promise _promise = promiseConverter.convertToEntity(promiseDao.updateById(id, promiseDto));
                return _promise;
            } catch (Exception e) {
                throw new CustomServerException();
            }
        }
        return null;
    }

    @Override
    public int deleteById(Long id) {
        try {
            promiseDao.deleteById(id);
        } catch (Exception e) {
            throw new CustomServerException();
        }
        return 0;
    }
}
