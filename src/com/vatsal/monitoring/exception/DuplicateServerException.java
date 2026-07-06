package com.vatsal.monitoring.exception;

public class DuplicateServerException extends RuntimeException {

    public DuplicateServerException(String message) {
        super(message);
    }
}