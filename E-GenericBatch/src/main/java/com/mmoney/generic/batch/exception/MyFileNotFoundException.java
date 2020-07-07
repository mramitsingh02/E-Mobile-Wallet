package com.mmoney.generic.batch.exception;

import java.net.MalformedURLException;

public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String s) {
        super(s);
    }

    public MyFileNotFoundException(String s, MalformedURLException ex) {
        super(s, ex);
    }
}
