package com.example.vs_server.validator;

import com.example.vs_server.model.Promise;

public interface PromiseValidator extends Validator<Promise> {
    boolean isValidTitle(String title);

    boolean validate(Promise promise);
}
