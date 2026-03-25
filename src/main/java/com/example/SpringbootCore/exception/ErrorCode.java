package com.example.SpringbootCore.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {PRODUCT_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "error.product_not_found"),
    PRODUCT_ALREADY_EXISTS(1002, HttpStatus.CONFLICT, "error.product_already_exists"),
    INVALID_PRICE(1003, HttpStatus.BAD_REQUEST, "error.invalid_price"),
    INVALID_PRODUCT_NAME(1004, HttpStatus.BAD_REQUEST, "error.invalid_product_name"),
    USER_NOT_FOUND(2001, HttpStatus.NOT_FOUND, "error.user_not_found"),
    USER_ALREADY_EXISTS(2002, HttpStatus.CONFLICT, "error.user_already_exists"),
    INVALID_CREDENTIALS(2003, HttpStatus.UNAUTHORIZED, "error.invalid_credentials"),
    VALIDATION_FAILED(4000, HttpStatus.BAD_REQUEST, "error.validation_failed"),
    INVALID_REQUEST(9001, HttpStatus.BAD_REQUEST, "error.invalid_request"),
    RESOURCE_NOT_FOUND(9002, HttpStatus.NOT_FOUND, "error.resource_not_found"),
    INTERNAL_ERROR(9999, HttpStatus.INTERNAL_SERVER_ERROR, "error.internal_error");

    private final int code;
    private final HttpStatus httpStatus;
    private final String messageKey;

    ErrorCode(int code, HttpStatus httpStatus, String messageKey) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.messageKey = messageKey;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
