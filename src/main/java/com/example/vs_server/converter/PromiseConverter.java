package com.example.vs_server.converter;

import com.example.vs_server.model.Promise;
import com.example.vs_server.model.PromiseDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PromiseConverter implements Converter<PromiseDto, Promise> {
    @Override
    public List<Promise> convertToEntities(Collection<PromiseDto> promiseDtos) {
        return promiseDtos.stream().map(this::convertToEntity).collect(Collectors.toList());
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
