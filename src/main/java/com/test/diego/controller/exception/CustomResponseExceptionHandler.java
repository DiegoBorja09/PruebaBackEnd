package com.test.diego.controller.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.test.diego.util.ApiErrorResponse;
import com.test.diego.util.exception.NotFoundException;

/**
 * Controlador de consejos global para manejar excepciones en toda la
 * aplicación. Proporciona manejo centralizado y personalización de las
 * respuestas para diferentes tipos de excepciones. Captura y procesa
 * excepciones específicas arrojadas durante la ejecución de controladores de
 * Spring MVC.
 * 
 * @author
 * @version
 * @since
 */
@ControllerAdvice
public class CustomResponseExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
		ApiErrorResponse error = new ApiErrorResponse(ex.getMessage(), request.getDescription(false),
				LocalDateTime.now(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
		ApiErrorResponse error = new ApiErrorResponse("Internal Server Error: " + ex.getMessage(),
				request.getDescription(false), LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}