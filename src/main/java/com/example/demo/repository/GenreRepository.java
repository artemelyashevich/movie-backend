package com.example.demo.repository;

import com.example.demo.entity.GenreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<GenreEntity, String> {
    Optional<GenreEntity> findByTitle(String title);
}
