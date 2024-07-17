package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.service.CategoryService;
import com.example.demo.util.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("categories")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(
            final @Valid @RequestBody CategoryDto dto,
            final BindingResult bindingResult,
            final UriComponentsBuilder uriComponentsBuilder
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        final CategoryEntity category = this.categoryService.create(dto);
        return ResponseEntity
                .created(
                        uriComponentsBuilder.replacePath("/api/v1/category/{categoryId}")
                                .build(Map.of("categoryId", category.getId())))
                .body(category);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<CategoryEntity> getById(final @PathVariable String categoryId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.categoryService.findById(categoryId));
    }

    @PatchMapping("{categoryId}")
    public ResponseEntity<Void> update(
            final @PathVariable String categoryId,
            final @Valid @RequestBody CategoryDto dto,
            final BindingResult bindingResult
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        this.categoryService.update(categoryId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<Void> delete(final @PathVariable String categoryId) {
        this.categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }
}
