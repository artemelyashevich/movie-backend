package com.example.demo.repository;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.GenreEntity;
import com.example.demo.entity.movie.MovieEntity;
import com.example.demo.entity.movie.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Locale;

public interface MovieRepository extends MongoRepository<MovieEntity, String> {

    List<MovieEntity> findAllByCategory(CategoryEntity category);

    List<MovieEntity> findAllByGenre(GenreEntity genre);

    List<MovieEntity> findAllByStatus(Status status);
}
