package com.example.challenge.converter;

import com.example.challenge.model.Promise;
import com.example.challenge.model.PromiseDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PromiseConverter implements Converter<PromiseDto, Promise> {
    @Override
    public List<Promise> convertToEntities(Collection<PromiseDto> promiseDtos) {
        return promiseDtos.stream().map(this::convertToEntity).toList();
    }

    @Override
    public Promise convertToEntity(PromiseDto promiseDto) {
        return new Promise(promiseDto.getId(), promiseDto.getTitle());
    }

    @Override
    public PromiseDto convertToDto(Promise promise) {
        PromiseDto promiseDto = new PromiseDto();
        promiseDto.setTitle(promise.getTitle());
        return promiseDto;
    }

}
