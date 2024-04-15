package com.patika.garantiservice.exceptions;

public class NoSuitableLoanException extends RuntimeException {
    public NoSuitableLoanException(String message) {
        super(message);
    }
}
