package com.example.demo.repository;

import com.example.demo.entity.ImageFile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImageRepository extends MongoRepository<ImageFile, String> {

    Optional<ImageFile> findByName(String name);
}
