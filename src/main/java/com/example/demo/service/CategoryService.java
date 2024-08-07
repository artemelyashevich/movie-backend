package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryEntity findById(String id);

    CategoryEntity findByName(String name);

    List<CategoryEntity> findAll();

    CategoryEntity create(CategoryDto dto);

    void update(String id, CategoryDto dto);

    void delete(String id);
}
