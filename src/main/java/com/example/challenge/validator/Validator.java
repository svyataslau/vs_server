package com.example.challenge.validator;

public interface Validator<T> {
    boolean validate(T t);
    
}
