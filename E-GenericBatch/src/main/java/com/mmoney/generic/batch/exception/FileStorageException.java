package com.mmoney.generic.batch.exception;

import java.io.IOException;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String s, Exception ex) {
        super(s, ex);
    }

    public FileStorageException(String s) {
        super(s);
    }
}
