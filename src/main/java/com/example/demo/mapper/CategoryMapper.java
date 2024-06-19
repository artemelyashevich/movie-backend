package com.example.demo.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.CategoryEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements DtoMapper<CategoryDto, CategoryEntity> {

    @NonNull
    @Override
    public CategoryEntity convertFromDto(CategoryDto dto) {
        return CategoryEntity.builder()
                .title(dto.title())
                .build();
    }
}
