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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
    public ResponseEntity<?> create(@Valid @RequestBody GenreDto dto,
                                    BindingResult bindingResult,
                                    UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Utils.validateBindingResult(bindingResult);
        final GenreEntity genre = this.genreService.create(dto);
        return ResponseEntity
                .created(
                        uriComponentsBuilder.replacePath("/genre/{genreId}")
                                .build(Map.of("genreId", genre.getId())))
                .body(genre);
    }

    @GetMapping("{genreId}")
    public ResponseEntity<GenreEntity> getById(@PathVariable String genreId) {
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(this.genreService.findById(genreId).orElseThrow(NoSuchElementException::new));
    }

    @PatchMapping("{genreId}")
    public ResponseEntity<Void> update(@PathVariable String genreId,
                                       @Valid @RequestBody GenreDto dto,
                                       BindingResult bindingResult) throws BindException {
        Utils.validateBindingResult(bindingResult);
        this.genreService.update(genreId, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{genreId}")
    public ResponseEntity<Void> delete(@PathVariable String genreId) {
        this.genreService.delete(genreId);
        return ResponseEntity.noContent().build();
    }
}
