package com.test.diego.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.diego.dto.UserDto;
import com.test.diego.service.UserService;
import com.test.diego.util.Util;

/**
 * Controlador para gestionar usuarios.
 * 
 * @author jhoncalderon
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
		UserDto userDto = userService.getUserById(id);
		return Util.buildResponse(userDto, HttpStatus.OK);
	}
}
