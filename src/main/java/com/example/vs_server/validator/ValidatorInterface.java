package com.example.vs_server.validator;

public interface ValidatorInterface<T> {
    boolean validate(T t);
}
