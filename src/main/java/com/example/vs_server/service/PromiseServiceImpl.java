package com.example.vs_server.service;

import com.example.vs_server.converter.PromiseConverter;
import com.example.vs_server.exception.CustomServerException;
import com.example.vs_server.model.Promise;
import com.example.vs_server.repository.PromiseDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromiseServiceImpl implements PromiseService {

    private final PromiseDao promiseDao;
    private final PromiseConverter promiseConverter;

    public PromiseServiceImpl(PromiseDao promiseDao, PromiseConverter promiseConverter) {
        this.promiseDao = promiseDao;
        this.promiseConverter = promiseConverter;
    }

    public List<Promise> getAllPromises() {
        try {
            return promiseConverter.convertToEntities(promiseDao.findAll());
        } catch (Exception e) {
            throw new CustomServerException();
        }
    }
}
