package com.example.demo.service.implementation;

import com.example.demo.dto.category.CategoryCreateDto;
import com.example.demo.dto.category.CategoryUpdateDto;
import com.example.demo.entity.CategoryEntity;
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

    @Override
    public Optional<CategoryEntity> findById(String id) {
        return Optional.ofNullable(this.categoryRepository.findById(id)
                .orElseThrow(NoSuchElementException::new));
    }

    @Override
    public List<CategoryEntity> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public CategoryEntity create(CategoryCreateDto dto) {
        return this.categoryRepository.save(new CategoryEntity(null, dto.title()));
    }

    @Override
    public void update(String id, CategoryUpdateDto dto) {
        this.categoryRepository.findById(id).ifPresentOrElse(
                category -> {
                    category.setTitle(dto.title());
                    this.categoryRepository.save(category);
                },
                () -> {
                    throw new NoSuchElementException();
                }
        );
    }

    @Override
    public void delete(String id) {
        this.categoryRepository.findById(id).ifPresentOrElse(
                this.categoryRepository::delete,
                () -> {
                    throw new NoSuchElementException();
                }
        );
    }
}
