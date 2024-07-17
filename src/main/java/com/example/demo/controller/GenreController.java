package com.example.demo.controller;

import com.example.demo.dto.GenreDto;
import com.example.demo.entity.GenreEntity;
import com.example.demo.service.GenreService;
import com.example.demo.util.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreEntity>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.genreService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(
            final @Valid @RequestBody GenreDto dto,
            final BindingResult bindingResult,
            final UriComponentsBuilder uriComponentsBuilder
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        final GenreEntity genre = this.genreService.create(dto);
        return ResponseEntity
                .created(
                        uriComponentsBuilder.replacePath("/api/v1/genre/{genreId}")
                                .build(Map.of("genreId", genre.getId())))
                .body(genre);
    }

    @GetMapping("{genreId}")
    public ResponseEntity<GenreEntity> getById(final @PathVariable String genreId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.genreService.findById(genreId));
    }

    @PatchMapping("{genreId}")
    public ResponseEntity<Void> update(
            final @PathVariable String genreId,
            final @Valid @RequestBody GenreDto dto,
            final BindingResult bindingResult
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        this.genreService.update(genreId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{genreId}")
    public ResponseEntity<Void> delete(final @PathVariable String genreId) {
        this.genreService.delete(genreId);
        return ResponseEntity.noContent().build();
    }
}
