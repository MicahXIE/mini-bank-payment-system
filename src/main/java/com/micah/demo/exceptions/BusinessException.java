package com.micah.demo.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String errorMessage) { super(errorMessage); }

    public BusinessException(String errorMessage, Throwable err) { super(errorMessage, err); }
}
