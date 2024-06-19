package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import javax.xml.stream.XMLInputFactory;

public record CategoryDto(
        @NotNull
        @Size(min = 3, max = 50)
        String title,

        @NotNull
        @Size(min = 3, max = 500)
        String details
) {
}
