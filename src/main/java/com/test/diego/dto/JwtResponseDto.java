package com.test.diego.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Contiene el token JWT y los roles asignados.
 */
@Data
@AllArgsConstructor
public class JwtResponseDto {
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;
    private Long userId;        
}
