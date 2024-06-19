package com.example.demo.service;

import com.example.demo.dto.category.CategoryCreateDto;
import com.example.demo.dto.category.CategoryUpdateDto;
import com.example.demo.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<CategoryEntity> findById(String id);

    List<CategoryEntity> findAll();

    CategoryEntity create(CategoryCreateDto dto);

    void update(String id, CategoryUpdateDto dto);

    void delete(String id);
}
