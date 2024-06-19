package com.example.demo.dto.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryCreateDto(
        @NotNull
        @Size(min = 3, max = 50)
        String title
) {
}
