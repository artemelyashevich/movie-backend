package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.movie.MovieEntity;
import com.example.demo.service.MovieService;
import com.example.demo.util.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieEntity>> getAll(
            @RequestParam("categoryName") String categoryName,
            @RequestParam("genreName") String genreName,
            @RequestParam("statusName") String statusName
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.movieService.findAll(categoryName, genreName, statusName));
    }

    @PostMapping
    public ResponseEntity<MovieEntity> create(@Valid @RequestBody MovieDto dto,
                                              BindingResult bindingResult,
                                              UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Utils.validateBindingResult(bindingResult);
        final MovieEntity movie = this.movieService.create(dto);
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .replacePath("movies/{movieId}")
                                .build(Map.of("movieId", movie.getId()))
                )
                .body(movie);
    }

    @GetMapping("{movieId}")
    public ResponseEntity<MovieEntity> getById(@PathVariable("movieId") String movieId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.movieService.findById(movieId).orElseThrow(NoSuchElementException::new));
    }

    @PatchMapping("{movieId}")
    public ResponseEntity<Void> update(@PathVariable("movieId") String movieId,
                                       @Valid @RequestBody MovieDto dto,
                                       BindingResult bindingResult) throws BindException {
        Utils.validateBindingResult(bindingResult);
        this.movieService.update(movieId, dto);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("{movieId}")
    public ResponseEntity<Void> delete(@PathVariable("movieId") String movieId) {
        this.movieService.delete(movieId);
        return ResponseEntity
                .noContent()
                .build();
    }

}