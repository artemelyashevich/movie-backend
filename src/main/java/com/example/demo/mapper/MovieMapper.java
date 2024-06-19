package com.example.demo.mapper;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.movie.MovieEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper implements DtoMapper<MovieDto, MovieEntity> {
    @Override
    @NonNull
    public MovieEntity convertFromDto(MovieDto dto) {
        return MovieEntity.builder()
                .description(dto.description())
                .releaseYear(dto.releaseYear())
                .title(dto.title())
                .size(dto.size())
                .rating(dto.rating())
                .duration(dto.duration())
                .quality(dto.quality())
                .languages(dto.languages())
                .watchLink(dto.watchLink())
                .imgUrl(dto.imgUrl())
                .bannerImgUrl(dto.bannerImgUrl())
                .subtitle(dto.subtitle())
                .status(dto.status())
                .releaseYear(dto.releaseYear())
                .build();
    }
}
