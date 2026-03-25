package com.example.SpringbootCore.exception;

import com.example.SpringbootCore.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    private String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    private String getMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        log.warn("Validation failed: {}", ex.getMessage());
        String message = getMessage("error.validation_failed");
        return ResponseEntity.badRequest()
                .body(ApiResponse.validation(message));
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException ex) {
        log.warn("AppException occurred: {}", ex.getMessage());
        ErrorCode errorCode = ex.getErrorCode();
        String key = "error." + errorCode.name().toLowerCase();
        String message = getMessage(key);
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.error(errorCode.getCode(), message));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDeniedException(
            AccessDeniedException ex) {
        log.warn("Access denied: {}", ex.getMessage());
        String message = getMessage("error.access_denied");
        return ResponseEntity.status(403)
                .body(ApiResponse.forbidden(message));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFound(
            EntityNotFoundException ex) {
        log.warn("Entity not found: {}", ex.getMessage());
        String message = getMessage("error.entity_not_found", new Object[]{ex.getEntity()});
        return ResponseEntity.status(404)
                .body(ApiResponse.notFound(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        log.error("Internal error: {}", ex.getMessage());
        String message = getMessage("error.internal_error");
        return ResponseEntity.status(500)
                .body(ApiResponse.exception(message));
    }
}
