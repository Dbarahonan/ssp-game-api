package com.db.ssp_game_api.rest;

import com.db.ssp_game_api.rest.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles validation exceptions from the request body, for example null values.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMissingParam(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .code("MISSING_PARAMETER")
                        .message("Missing parameters in the request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(System.currentTimeMillis())
                        .build());
    }

    // Handles conversion errors, for example an invalid enum
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .code("INVALID_PARAMETER")
                        .message("Invalid parameters in the request")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(System.currentTimeMillis())
                        .build());
    }

    // Generic exception handler for all other cases
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorResponse.builder()
                                .message("Unexpected error: " + ex.getMessage())
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .timestamp(System.currentTimeMillis())
                                .build()
                );
    }
}
