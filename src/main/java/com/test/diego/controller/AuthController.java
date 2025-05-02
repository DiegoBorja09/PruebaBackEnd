package com.test.diego.controller;

import com.test.diego.dto.*;
import com.test.diego.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponseDto register(
        @Valid @RequestBody RegisterRequestDto dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public JwtResponseDto login(
        @Valid @RequestBody LoginRequestDto dto) {
        return authService.login(dto);
    }
}
