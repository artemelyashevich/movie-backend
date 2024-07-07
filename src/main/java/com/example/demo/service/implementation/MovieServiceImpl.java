package com.example.demo.service.implementation;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.GenreEntity;
import com.example.demo.entity.movie.MovieEntity;
import com.example.demo.entity.movie.Status;
import com.example.demo.mapper.DtoMapper;
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
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DtoMapper<MovieDto, MovieEntity> dtoMapper;
    private final CategoryService categoryService;
    private final GenreService genreService;

    @Override
    public Optional<MovieEntity> findById(String id) {
        return Optional.ofNullable(
                this.movieRepository.findById(id).orElseThrow(() ->
                        new NoSuchElementException("Where no movie with such id: %s".formatted(id))
                )
        );
    }

    @Override
    public List<MovieEntity> findByStatus(String statusName) {
        return this.movieRepository.findAllByStatus(
                Status.valueOf(statusName)
        );
    }

    @Override
    public List<MovieEntity> findAll(String categoryName, String genreName, String statusName, String query) {
        if (query != null && !query.isEmpty()) {
            return this.movieRepository.findByTitle(query);
        }
        if (categoryName != null && !categoryName.isEmpty()) {
            return this.movieRepository.findAllByCategories(
                    List.of(this.categoryService.findByName(categoryName)
                            .orElseThrow(NoSuchElementException::new))
            );
        }
        if (genreName != null && !genreName.isEmpty()) {
            return this.movieRepository.findAllByGenres(
                    List.of(this.genreService.findByName(genreName)
                            .orElseThrow(NoSuchElementException::new))
            );
        }
        if (statusName != null && !statusName.isEmpty()) {
            return this.movieRepository.findAllByStatus(
                    Status.valueOf(statusName)
            );
        }
        return this.movieRepository.findAll();
    }

    @Override
    public Page<MovieEntity> findPaginated(Integer page, Integer size) {
        final Pageable pageable = PageRequest.of(page, size);
        Page<MovieEntity> movies = this.movieRepository.findAll(pageable);
        return movies;
    }

    @Override
    public MovieEntity create(MovieDto dto) {
        List<GenreEntity> genres = dto.genres().stream()
                .map(
                        genre -> genreService.findByName(genre)
                                .orElseThrow(() ->
                                        new NoSuchElementException("Where no such genre: %s".formatted(genre))
                                )
                )
                .toList();
        List<CategoryEntity> categories = dto.categories().stream()
                .map(
                        category -> categoryService.findByName(category)
                                .orElseThrow((() ->
                                        new NoSuchElementException("Where no such category: %s".formatted(category))
                                ))
                )
                .toList();
        MovieEntity movie = dtoMapper.convertFromDto(dto);
        movie.setGenres(genres);
        movie.setCategories(categories);
        return movieRepository.save(movie);
    }

    @Override
    public void update(String id, MovieDto dto) {
        this.movieRepository.findById(id).ifPresentOrElse(
                movie -> {
                    List<GenreEntity> genres = dto.genres().stream()
                            .map(
                                    genre -> genreService.findByName(genre)
                                            .orElseThrow((() ->
                                                    new NoSuchElementException(
                                                            "Where no such genre: %s"
                                                                    .formatted(genre)
                                                    )
                                            ))
                            )
                            .toList();
                    List<CategoryEntity> categories = dto.categories().stream()
                            .map(
                                    category -> categoryService.findByName(category)
                                            .orElseThrow((() ->
                                                    new NoSuchElementException(
                                                            "Where no such category: %s"
                                                                    .formatted(category)
                                                    )
                                            ))
                            )
                            .toList();
                    movie.setCategories(categories);
                    movie.setGenres(genres);
                    movie.setDuration(dto.duration());
                    movie.setDescription(dto.description());
                    movie.setRating(dto.rating());
                    movie.setQuality(dto.quality());
                    movie.setStatus(dto.status());
                    movie.setBannerImgUrl(dto.bannerImgUrl());
                    movie.setImgUrl(dto.imgUrl());
                    movie.setSize(dto.size());
                    movie.setSubtitle(dto.subtitle());
                    movie.setTitle(dto.title());
                    movie.setLanguages(dto.languages());
                    movie.setReleaseYear(dto.releaseYear());
                    movie.setWatchLink(dto.watchLink());
                    this.movieRepository.save(movie);
                },
                () -> {
                    throw new NoSuchElementException("Where no movie with such id: %s".formatted(id));
                }
        );
    }

    @Override
    public void delete(String id) {
        this.movieRepository.findById(id).ifPresentOrElse(
                movieRepository::delete,
                () -> {
                    throw new NoSuchElementException("Where no movie with such id: %s".formatted(id));
                }
        );
    }
}