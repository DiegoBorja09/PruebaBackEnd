package com.test.diego.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

	private Util() {
		throw new IllegalStateException("Clase de utilidad");
	}

	/**
	 * Construye una respuesta HTTP personalizada con el código HTTP especificado.
	 * 
	 * @param  <T>        Tipo del objeto que se quiere incluir en la respuesta.
	 * @param  response   El objeto de respuesta.
	 * @param  httpStatus El código HTTP de respuesta.
	 * @return            Una ResponseEntity que contiene el objeto de respuesta y
	 *                    el código HTTP especificado.
	 */
	public static <T> ResponseEntity<T> buildResponse(T response, HttpStatus httpStatus) {
		return new ResponseEntity<>(response, httpStatus);
	}
}
