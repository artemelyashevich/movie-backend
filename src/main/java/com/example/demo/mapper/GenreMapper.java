package com.example.demo.mapper;

import com.example.demo.dto.GenreDto;
import com.example.demo.entity.GenreEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements DtoMapper<GenreDto, GenreEntity> {

    @NonNull
    @Override
    public GenreEntity convertFromDto(GenreDto dto) {
        return GenreEntity.builder()
                .title(dto.title())
                .build();
    }
}
