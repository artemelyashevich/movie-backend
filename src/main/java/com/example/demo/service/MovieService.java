package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.movie.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    MovieEntity findById(String id);

    MovieEntity uploadImage(MultipartFile file, String id) throws IOException;

    MovieEntity uploadBannerImage(MultipartFile file, String id) throws IOException;

    List<MovieEntity> findByStatus(String statusName);

    List<MovieEntity> findAll(String categoryName, String genreName, String statusName, String query);

    Page<MovieEntity> findPaginated(Integer page, Integer size);

    MovieEntity create(MovieDto dto);

    void update(String id, MovieDto dto);

    void delete(String id);
}
