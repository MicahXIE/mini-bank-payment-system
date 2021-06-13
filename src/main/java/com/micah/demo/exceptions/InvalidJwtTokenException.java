package com.micah.demo.exceptions;

public class InvalidJwtTokenException extends BusinessException {
    public InvalidJwtTokenException(String errorMessage) { super(errorMessage); }

    public InvalidJwtTokenException(String errorMessage, Throwable err) { super(errorMessage, err); }
}
