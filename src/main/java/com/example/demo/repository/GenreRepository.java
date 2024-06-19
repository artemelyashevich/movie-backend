package com.example.demo.repository;

import com.example.demo.entity.GenreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<GenreEntity, String> {
}
