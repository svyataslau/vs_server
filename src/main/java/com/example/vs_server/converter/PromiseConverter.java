package com.example.vs_server.converter;

import com.example.vs_server.model.Promise;
import com.example.vs_server.model.PromiseDto;
import org.springframework.stereotype.Component;

@Component
public class PromiseConverter extends Converter<PromiseDto, Promise> {

    public PromiseConverter() {
        super(PromiseConverter::convertToEntity);
    }

    public static Promise convertToEntity(PromiseDto dto) {
        return new Promise(dto.getId(), dto.getTitle());
    }

    public static PromiseDto convertToDto(Promise promise) {
        return new PromiseDto(promise.getTitle());
    }

}
