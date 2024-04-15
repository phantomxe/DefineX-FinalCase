package com.patika.kredinbizdeservice.exceptions;

public class NoSuitableLoanException extends RuntimeException {
    public NoSuitableLoanException(String message) {
        super(message);
    }
}
