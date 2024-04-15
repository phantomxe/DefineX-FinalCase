package com.patika.akbankservice.exceptions;

public class NoSuitableLoanException extends RuntimeException {
    public NoSuitableLoanException(String message) {
        super(message);
    }
}
