package com.example.demo.mapper;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.movie.MovieEntity;
import com.example.demo.mapper.contract.DtoMapper;
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

    @Override
    public MovieEntity update(MovieEntity movie, MovieDto dto) {
        movie.setDuration(dto.duration());
        movie.setDescription(dto.description());
        movie.setRating(dto.rating());
        movie.setQuality(dto.quality());
        movie.setStatus(dto.status());
        movie.setBannerImgUrl(dto.bannerImgUrl());
        movie.setImgUrl(dto.imgUrl());
        movie.setSize(dto.size());
        movie.setSubtitle(dto.subtitle());
        movie.setTitle(dto.title());
        movie.setLanguages(dto.languages());
        movie.setReleaseYear(dto.releaseYear());
        movie.setWatchLink(dto.watchLink());
        return movie;
    }
}
