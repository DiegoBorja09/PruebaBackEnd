package com.test.diego.service;

import com.test.diego.dto.UserDto;

/**
 * Interfaz que define los metodos para Usuarios.
 * 
 * @author
 */
public interface UserService {

	/**
	 * Obtiene un usuario por el id.
	 * 
	 * @param  id
	 * @return    userDto
	 */
	public UserDto getUserById(Long id);
}
