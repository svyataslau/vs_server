package com.example.vs_server.converter;

import java.util.function.Function;

public class Converter<T, U> {

    private final Function<T, U> convertToEntity;
    private final Function<U, T> convertToDto;

    public Converter(Function<T, U> convertToEntity, Function<U, T> convertToDto) {
        this.convertToEntity = convertToEntity;
        this.convertToDto = convertToDto;
    }

    public final U convertFromDto(final T dto) {
        return convertToEntity.apply(dto);
    }

    public final T convertFromEntity(final U entity) {
        return convertToDto.apply(entity);
    }
}
