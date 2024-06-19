package com.example.demo.dto;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.GenreEntity;
import com.example.demo.entity.movie.Quality;
import com.example.demo.entity.movie.Rating;
import com.example.demo.entity.movie.Size;
import com.example.demo.entity.movie.Status;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import java.util.List;

public record MovieDto(
        @NotNull
        @jakarta.validation.constraints.Size(min = 2, max = 100)
        String title,

        @NotNull
        @jakarta.validation.constraints.Size(min = 2, max = 1500)
        String description,

        @NotNull
        Rating rating,

        @NotNull
        List<String> languages,

        @NotNull
        @URL
        String bannerImgUrl,

        @NotNull
        @URL
        String imgUrl,

        @NotNull
        @jakarta.validation.constraints.Size(min = 2)
        String duration,

        @NotNull
        @URL
        String watchLink,

        @NotNull
        Quality quality,

        @NotNull
        Status status,

        @NotNull
        Integer releaseYear,

        @NotNull
        String subtitle,

        @NotNull
        Size size,

        @NotNull
        List<String> genres,

        @NotNull
        List<String> categories
) {
}
