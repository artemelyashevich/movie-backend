package com.example.demo.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpDto(
        @NotBlank(message = "Invalid Name: Empty name")
        @NotNull(message = "Invalid Name: Name is NULL")
        @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
        String username,


        @NotBlank(message = "Invalid Email: Empty email")
        @NotNull(message = "Invalid Email: Email is NULL")
        @Email
        String email,

        @NotBlank(message = "Invalid Password: Empty password")
        @NotNull(message = "Invalid Password: Password is NULL")
        @Size(min = 5, message = "Minimum password length is 2 characters")
        String password
) {
}
