package com.example.demo.service.implementation;

import com.example.demo.entity.movie.MovieEntity;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public Optional<MovieEntity> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<MovieEntity> findAll() {
        return null;
    }

    @Override
    public MovieEntity create() {
        return null;
    }

    @Override
    public MovieEntity update(String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}