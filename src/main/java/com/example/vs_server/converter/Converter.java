package com.example.vs_server.converter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Converter<T, U> {

    private final Function<T, U> convertToEntity;

    public Converter(final Function<T, U> convertToEntity) {
        this.convertToEntity = convertToEntity;
    }

    public final U convertFromDto(final T dto) {
        return convertToEntity.apply(dto);
    }

    public final List<U> convertToEntities(final Collection<T> dtos) {
        return dtos.stream().map(this::convertFromDto).collect(Collectors.toList());
    }
}
