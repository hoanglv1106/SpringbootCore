package com.example.SpringbootCore.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("code",ErrorCode.VALIDATION_FAILED.getCode());
        body.put("status",400);
        body.put("error","VALIDATION_FAILED");
        body.put("message",errors);
        return  ResponseEntity.badRequest().body(body);

    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppException(AppException ex) {
        return buildResponse(ex.getErrorCode(), ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
            return  buildResponse(ErrorCode.INTERNAL_ERROR,ErrorCode.INTERNAL_ERROR.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildResponse(ErrorCode errorCode, String message) {
        Map<String , Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("code", errorCode.getCode());
        body.put("status", errorCode.getHttpStatus().value());
        body.put("error", errorCode.name());
        body.put("message", message);
        return ResponseEntity.status(errorCode.getHttpStatus()).body(body);

    }


}
