package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.movie.MovieEntity;
import com.example.demo.service.MovieService;
import com.example.demo.util.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieEntity>> getAll(
            final @RequestParam(value = "categoryName", required = false) String categoryName,
            final @RequestParam(value = "genreName", required = false) String genreName,
            final @RequestParam(value = "statusName", required = false) String statusName,
            final @RequestParam(value = "query", required = false) String query
    ) {
        return ResponseEntity
                .ok()
                .body(this.movieService.findAll(categoryName, genreName, statusName, query));
    }

    @GetMapping("paginated")
    public ResponseEntity<Page<MovieEntity>> getPaginated(
            final @RequestParam(value = "page") Integer page,
            final @RequestParam("size") Integer size
    ) {
        return ResponseEntity
                .ok()
                .body(this.movieService.findPaginated(page, size));
    }

    @PostMapping
    public ResponseEntity<MovieEntity> create(
            final @Valid @RequestBody MovieDto dto,
            final BindingResult bindingResult,
            final UriComponentsBuilder uriComponentsBuilder
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        final MovieEntity movie = this.movieService.create(dto);
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .replacePath("/api/v1/movies/{movieId}")
                                .build(Map.of("movieId", movie.getId()))
                )
                .body(movie);
    }

    @GetMapping("{movieId}")
    public ResponseEntity<MovieEntity> getById(final @PathVariable("movieId") String movieId) {
        return ResponseEntity
                .ok()
                .body(this.movieService.findById(movieId));
    }

    @PatchMapping("{movieId}")
    public ResponseEntity<Void> update(
            final @PathVariable("movieId") String movieId,
            final @Valid @RequestBody MovieDto dto,
            final BindingResult bindingResult
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        this.movieService.update(movieId, dto);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("{movieId}")
    public ResponseEntity<Void> delete(final @PathVariable("movieId") String movieId) {
        this.movieService.delete(movieId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("/status/{statusName}")
    public ResponseEntity<List<MovieEntity>> findAllByStatus(
            final @PathVariable("statusName") String status
    ) {
        return ResponseEntity
                .ok()
                .body(this.movieService.findByStatus(status));
    }
}