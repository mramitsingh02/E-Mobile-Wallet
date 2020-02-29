package com.tester.spring.rest.webservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputFoundException extends RuntimeException {
    public InvalidInputFoundException(String messages) {
        super(messages);
    }
}
