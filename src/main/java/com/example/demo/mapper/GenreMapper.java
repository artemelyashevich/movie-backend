package com.example.demo.mapper;

import com.example.demo.dto.GenreDto;
import com.example.demo.entity.GenreEntity;
import com.example.demo.mapper.contract.DtoMapper;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements DtoMapper<GenreDto, GenreEntity> {

    @NonNull
    @Override
    public GenreEntity convertFromDto(GenreDto dto) {
        return GenreEntity.builder()
                .title(dto.title())
                .details(dto.details())
                .build();
    }

    @Override
    public GenreEntity update(GenreEntity entity, GenreDto dto) {
        return null;
    }
}
