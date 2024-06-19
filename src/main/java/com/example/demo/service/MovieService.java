package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.movie.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<MovieEntity> findById(String id);

    List<MovieEntity> findAll();

    MovieEntity create(MovieDto dto);

    void update(String id, MovieDto dto);

    void delete(String id);
}
