package com.example.demo.service;

import com.example.demo.dto.GenreDto;
import com.example.demo.entity.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<GenreEntity> findAll();

    GenreEntity findById(String id);

    GenreEntity findByName(String name);

    GenreEntity create(GenreDto genreCreateDto);

    void update(String id, GenreDto name);

    void delete(String id);
}
