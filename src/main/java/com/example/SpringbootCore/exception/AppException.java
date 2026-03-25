package com.example.SpringbootCore.exception;

//AppException giúp attach ErrorCode → kiểm soát HTTP status + error code + message
public class AppException extends RuntimeException {

    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}