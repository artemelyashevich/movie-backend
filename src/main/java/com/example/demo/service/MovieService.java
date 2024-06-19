package com.example.demo.service;

import com.example.demo.entity.movie.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<MovieEntity> findById(String id);

    List<MovieEntity> findAll();

    MovieEntity create();

    MovieEntity update(String id);

    void delete(String id);
}
