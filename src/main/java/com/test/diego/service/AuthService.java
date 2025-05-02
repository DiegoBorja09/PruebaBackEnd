package com.test.diego.service;

import com.test.diego.dto.JwtResponseDto;
import com.test.diego.dto.LoginRequestDto;
import com.test.diego.dto.RegisterRequestDto;

public interface AuthService {
    JwtResponseDto register(RegisterRequestDto dto);
    JwtResponseDto login(LoginRequestDto dto);
}
