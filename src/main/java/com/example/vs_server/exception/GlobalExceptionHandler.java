package com.example.vs_server.exception;

import com.example.vs_server.response.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ResponseFactory responseFactory;

    public GlobalExceptionHandler(ResponseFactory response) {
        this.responseFactory = response;
    }


    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<String> handleInvalidFieldException(InvalidFieldException exception) {
        return responseFactory.generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException exception) {
        return responseFactory.generateResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomServerException.class)
    public ResponseEntity<String> handleException() {
        return responseFactory.generateResponse("Something has gone wrong...", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
