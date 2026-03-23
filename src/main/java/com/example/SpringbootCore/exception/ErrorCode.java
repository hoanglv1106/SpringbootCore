package com.example.SpringbootCore.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // Product errors
    PRODUCT_NOT_FOUND(1001, HttpStatus.NOT_FOUND),
    PRODUCT_ALREADY_EXISTS(1002, HttpStatus.CONFLICT),
    INVALID_PRICE(1003, HttpStatus.BAD_REQUEST),
    INVALID_PRODUCT_NAME(1004, HttpStatus.BAD_REQUEST),

    // User errors
    USER_NOT_FOUND(2001, HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(2002, HttpStatus.CONFLICT),
    INVALID_CREDENTIALS(2003, HttpStatus.UNAUTHORIZED),

    // General errors
    VALIDATION_FAILED(4000, HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(9001, HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(9002, HttpStatus.NOT_FOUND),
    INTERNAL_ERROR(9999, HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final HttpStatus httpStatus;

    ErrorCode(int code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
