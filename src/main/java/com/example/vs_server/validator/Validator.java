package com.example.vs_server.validator;

public interface Validator<T> {
    boolean validate(T t);
    
}
