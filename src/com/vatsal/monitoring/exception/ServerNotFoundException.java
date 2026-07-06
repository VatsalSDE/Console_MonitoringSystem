package com.vatsal.monitoring.exception;

public class ServerNotFoundException extends RuntimeException {

    public ServerNotFoundException(String message) {
        super(message);
    }

}