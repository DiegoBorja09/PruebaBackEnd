package com.test.diego.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class RegisterRequestDto {
    @NotBlank private String username;
    @NotBlank private String password;
}
