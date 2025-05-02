package com.test.diego.service.impl;

import com.test.diego.dto.JwtResponseDto;
import com.test.diego.dto.LoginRequestDto;
import com.test.diego.dto.RegisterRequestDto;
import com.test.diego.entity.Role;
import com.test.diego.entity.UserEntity;
import com.test.diego.repository.UserRepository;
import com.test.diego.service.AuthService;
import com.test.diego.util.JwtTokenProvider;
import com.test.diego.util.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Override
    public JwtResponseDto register(RegisterRequestDto dto) {
        // 1) Validar que no exista ya el username
        if (userRepo.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException(dto.getUsername());
        }

        // 2) Crear la entidad y guardarla
        UserEntity user = UserEntity.builder()
            .username(dto.getUsername())
            .password(passwordEncoder.encode(dto.getPassword()))
            .build();
        user.getRoles().add(Role.ROLE_USER);
        user = userRepo.save(user);

        // 3) Generar el token incluyendo el userId
        String token = tokenProvider.generateToken(
            user.getUsername(),
            user.getRoles().stream().map(Enum::name).collect(Collectors.toList()),
            user.getId()    // ← userId aquí
        );

        // 4) Devolver el DTO con el userId
        return new JwtResponseDto(
            token,
            "Bearer",
            user.getUsername(),
            user.getRoles().stream().map(Enum::name).collect(Collectors.toList()),
            user.getId()    // ← y userId aquí
        );
    }

    @Override
    public JwtResponseDto login(LoginRequestDto dto) {
        // 1) Autenticar credenciales
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        // 2) Cargar la entidad de usuario para tener su ID
        UserEntity user = userRepo.findByUsername(dto.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // 3) Generar el token incluyendo el userId
        String token = tokenProvider.generateToken(
            user.getUsername(),
            user.getRoles().stream().map(Enum::name).collect(Collectors.toList()),
            user.getId()    // ← userId aquí
        );

        // 4) Devolver el DTO con el userId
        return new JwtResponseDto(
            token,
            "Bearer",
            user.getUsername(),
            user.getRoles().stream().map(Enum::name).collect(Collectors.toList()),
            user.getId()    // ← y userId aquí
        );
    }
}
