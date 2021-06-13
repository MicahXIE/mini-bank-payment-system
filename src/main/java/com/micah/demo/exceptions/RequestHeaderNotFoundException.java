package com.micah.demo.exceptions;

public class RequestHeaderNotFoundException extends NotFoundException {
    public RequestHeaderNotFoundException(String message) {
        super(message);
    }
}
