package com.example.demo.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignInDto(
        @NotBlank(message = "Invalid Username: Empty username")
        @NotNull(message = "Invalid Username: Username is NULL")
        String username,

        @NotBlank(message = "Invalid Password: Empty password")
        @NotNull(message = "Invalid Password: Password is NULL")
        String password
) {
}
