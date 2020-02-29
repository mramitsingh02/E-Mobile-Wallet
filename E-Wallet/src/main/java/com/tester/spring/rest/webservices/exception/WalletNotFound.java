package com.tester.spring.rest.webservices.exception;

public class WalletNotFound extends NotFoundException {
    public WalletNotFound(String msg) {
        super(msg);
    }
}
