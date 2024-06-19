package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GenreDto(
        @NotNull
        @Size(min = 3, max = 50)
        String title
) {
}
