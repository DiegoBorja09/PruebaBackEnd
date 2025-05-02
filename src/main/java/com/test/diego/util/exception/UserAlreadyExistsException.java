package com.test.diego.util.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Se lanza cuando se intenta registrar un usuario cuyo nombre de usuario ya existe.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("El nombre de usuario ya está en uso: " + username);
    }

    public UserAlreadyExistsException(String username, Throwable cause) {
        super("El nombre de usuario ya está en uso: " + username, cause);
    }
}
