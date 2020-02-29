package com.tester.spring.rest.webservices.exception;

public class NotMatchingFoundException extends NotFoundException {
    public NotMatchingFoundException(String s) {
        super(s);
    }
}
