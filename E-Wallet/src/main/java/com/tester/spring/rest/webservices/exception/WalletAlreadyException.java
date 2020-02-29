package com.tester.spring.rest.webservices.exception;

public class WalletAlreadyException extends AlreadyExistsException {
    public WalletAlreadyException(String msg) {
        super(msg);
    }
}
