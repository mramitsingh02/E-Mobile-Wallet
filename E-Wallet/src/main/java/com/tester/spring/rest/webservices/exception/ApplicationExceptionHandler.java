package com.tester.spring.rest.webservices.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.function.Supplier;

public class ApplicationExceptionHandler {


    public static Supplier<WalletNotFound> throwWalletNotFound(MessageSource messageSource, String msisdn) {
        return () -> new WalletNotFound (messageSource.getMessage ("msisdn.not.found", new Object[]{msisdn}, LocaleContextHolder.getLocale ()));
    }

    public static Supplier<WalletNotFound> throwWalletNumberNotFound(MessageSource messageSource, String walletNumber) {
        return () -> new WalletNotFound (walletNumber + " Wallet number not found.");
    }
}
