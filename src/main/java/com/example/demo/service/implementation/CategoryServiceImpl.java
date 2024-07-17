package com.example.demo.service.implementation;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.mapper.contract.DtoMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final DtoMapper<CategoryDto, CategoryEntity> dtoMapper;

    @Override
    public CategoryEntity findById(final String id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("No such category with id: %s".formatted(id))
                );
    }

    @Override
    public CategoryEntity findByName(final String name) {
        return
                this.categoryRepository.findByTitle(name)
                        .orElseThrow(() ->
                                new NoSuchElementException("No such category with name: %s".formatted(name))
                        );
    }

    @Override
    public List<CategoryEntity> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public CategoryEntity create(final CategoryDto dto) {
        return this.categoryRepository.save(this.dtoMapper.convertFromDto(dto));
    }

    @Override
    public void update(final String id, final CategoryDto dto) {
        final CategoryEntity category = this.findById(id);
        category.setTitle(dto.title());
        category.setDetails(dto.details());
        this.categoryRepository.save(category);
    }

    @Override
    public void delete(final String id) {
        final CategoryEntity category = this.findById(id);
        this.categoryRepository.delete(category);
    }
}
