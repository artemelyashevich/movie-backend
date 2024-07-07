package com.example.demo.repository;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.GenreEntity;
import com.example.demo.entity.movie.MovieEntity;
import com.example.demo.entity.movie.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MovieRepository extends MongoRepository<MovieEntity, String> {

    List<MovieEntity> findAllByCategories(List<CategoryEntity> category);

    List<MovieEntity> findAllByGenres(List<GenreEntity> genre);

    List<MovieEntity> findAllByStatus(Status status);

    @Query("{title:{$regex:?0}}")
    List<MovieEntity> findByTitle(String query);
}
