package com.example.demo.service.implementation;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.GenreEntity;
import com.example.demo.entity.movie.MovieEntity;
import com.example.demo.entity.movie.Status;
import com.example.demo.mapper.contract.DtoMapper;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.GenreService;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DtoMapper<MovieDto, MovieEntity> dtoMapper;
    private final CategoryService categoryService;
    private final GenreService genreService;

    @Override
    public MovieEntity findById(final String id) {
        return this.movieRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Where no movie with such id: %s".formatted(id))
        );
    }

    @Override
    public List<MovieEntity> findByStatus(final String statusName) {
        return this.movieRepository.findAllByStatus(
                Status.valueOf(statusName)
        );
    }

    @Override
    public List<MovieEntity> findAll(String categoryName, String genreName, String statusName, final String query) {
        if (query != null && !query.isEmpty()) {
            return this.findByTitle(query);
        }
        if (categoryName != null && !categoryName.isEmpty()) {
            return this.findByCategory(categoryName);
        }
        if (genreName != null && !genreName.isEmpty()) {
            return this.findByGenre(genreName);
        }
        if (statusName != null && !statusName.isEmpty()) {
            return this.findByStatus(statusName);
        }
        return this.movieRepository.findAll();
    }

    @Override
    public Page<MovieEntity> findPaginated(final Integer page, final Integer size) {
        final Pageable pageable = PageRequest.of(page, size);
        return this.movieRepository.findAll(pageable);
    }

    @Override
    public MovieEntity create(final MovieDto dto) {
        final MovieEntity movie = dtoMapper.convertFromDto(dto);
        movie.setGenres(
                dto.genres().stream()
                        .map(genreService::findByName)
                        .toList()
        );
        movie.setCategories(
                dto.categories().stream()
                        .map(this.categoryService::findByName)
                        .toList()
        );
        return movieRepository.save(movie);
    }

    @Override
    public void update(final String id, final MovieDto dto) {
        MovieEntity movie = this.findById(id);
        List<GenreEntity> genres = dto.genres().stream()
                .map(this.genreService::findByName)
                .toList();
        List<CategoryEntity> categories = dto.categories().stream()
                .map(this.categoryService::findByName)
                .toList();
        movie = this.dtoMapper.update(movie, dto);
        movie.setCategories(categories);
        movie.setGenres(genres);
        this.movieRepository.save(movie);
    }

    @Override
    public void delete(final String id) {
        final MovieEntity movie = this.findById(id);
        this.movieRepository.delete(movie);
    }

    private List<MovieEntity> findByTitle(final String query) {
        return this.movieRepository.findByTitle(query);
    }

    private List<MovieEntity> findByGenre(final String genre) {
        return this.movieRepository.findAllByGenres(List.of(this.genreService.findByName(genre)));
    }

    private List<MovieEntity> findByCategory(final String category) {
        return this.movieRepository.findAllByCategories(List.of(this.categoryService.findByName(category)));
    }
}