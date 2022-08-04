package com.example.vs_server.exception;

import com.example.vs_server.response.ResponseFactoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ResponseFactoryImpl responseFactory;

    public GlobalExceptionHandler(ResponseFactoryImpl response) {
        this.responseFactory = response;
    }


    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmailException (InvalidEmailException exception){
        return responseFactory.generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException (InvalidPasswordException exception){
        return responseFactory.generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidNicknameException.class)
    public ResponseEntity<String> handleInvalidNicknameException (InvalidNicknameException exception){
        return responseFactory.generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException (UnauthorizedException exception){
        return responseFactory.generateResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomServerException.class)
    public ResponseEntity<String> handleException (CustomServerException exception){
        return responseFactory.generateResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
