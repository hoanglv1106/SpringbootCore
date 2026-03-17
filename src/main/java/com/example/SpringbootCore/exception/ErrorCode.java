package com.example.SpringbootCore.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // Product errors
    PRODUCT_NOT_FOUND(1001, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_ALREADY_EXISTS(1002, "Product already exists", HttpStatus.CONFLICT),
    INVALID_PRICE(1003, "Price must be greater than 0", HttpStatus.BAD_REQUEST),
    INVALID_PRODUCT_NAME(1004, "Product name must not be blank", HttpStatus.BAD_REQUEST),

    // User errors
    USER_NOT_FOUND(2001, "User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS(2002, "User already exists", HttpStatus.CONFLICT),
    INVALID_CREDENTIALS(2002, "Invalid username or password", HttpStatus.UNAUTHORIZED),

    // General errors
    VALIDATION_FAILED(4000, "Validation failed", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(9001, "Invalid request", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(9002, "Resource not found", HttpStatus.NOT_FOUND),
    INTERNAL_ERROR(9999, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);




    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public HttpStatus getHttpStatus() { return httpStatus; }
}
