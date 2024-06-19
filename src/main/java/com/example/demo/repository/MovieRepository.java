package com.example.demo.repository;

import com.example.demo.entity.movie.MovieEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<MovieEntity, String> {
}
