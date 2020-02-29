package com.tester.spring.rest.webservices.utils;

import java.security.SecureRandom;

@FunctionalInterface
public interface PinGenerator {
    static String newPin() {
        return String.valueOf(new SecureRandom().nextInt(99999));
    }

    String pin();

}
