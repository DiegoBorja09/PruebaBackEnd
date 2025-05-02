package com.test.diego.controller.exception;

import com.test.diego.util.ApiError;
import com.test.diego.util.exception.LoanNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(LoanNotFoundException ex) {
        ApiError err = new ApiError("NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleValidation(ConstraintViolationException ex) {
        ApiError err = new ApiError("VALIDATION_ERROR", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
