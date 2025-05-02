package com.test.diego.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.test.diego.dto.UserDto;
import com.test.diego.entity.UserEntity;
import com.test.diego.repository.UserRepository;
import com.test.diego.service.UserService;
import com.test.diego.util.exception.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private ModelMapper modelMapper;

	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	/**
	 * Buscar un usuario por el id y lo devuelve. Si no encuentre el is lanza la
	 * excepcion NotFoundException.
	 */
	@Override
	public UserDto getUserById(Long id) {
		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));
		return modelMapper.map(user, UserDto.class);
	}
}
