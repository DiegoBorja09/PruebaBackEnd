package com.test.diego.util;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

	private String message;

	private String path;

	private LocalDateTime timestamp;

	private int status;
}
