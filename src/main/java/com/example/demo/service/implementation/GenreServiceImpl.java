package com.example.demo.service.implementation;

import com.example.demo.dto.GenreDto;
import com.example.demo.entity.GenreEntity;
import com.example.demo.mapper.contract.DtoMapper;
import com.example.demo.repository.GenreRepository;
import com.example.demo.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final DtoMapper<GenreDto, GenreEntity> genreMapper;

    @Override
    public List<GenreEntity> findAll() {
        return this.genreRepository.findAll();
    }

    @Override
    public GenreEntity findById(final String id) {
        return this.genreRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("No such genre with id: %s".formatted(id))
                );

    }

    @Override
    public GenreEntity findByName(final String name) {
        return
                this.genreRepository.findByTitle(name).orElseThrow(() ->
                        new NoSuchElementException("No such genre with name: %s".formatted(name))
                );
    }

    @Override
    public GenreEntity create(final GenreDto dto) {
        return this.genreRepository.save(this.genreMapper.convertFromDto(dto));
    }

    @Override
    public void update(final String id, final GenreDto dto) {
        this.genreRepository.findById(id)
                .ifPresentOrElse(
                        genre -> {
                            genre.setTitle(dto.title());
                            genreRepository.save(genre);
                        },
                        () -> {
                            throw new NoSuchElementException("No such genre with id: %s".formatted(id));
                        }
                );
    }

    @Override
    public void delete(final String id) {
        this.genreRepository.findById(id)
                .ifPresentOrElse(
                        genreRepository::delete,
                        () -> {
                            throw new NoSuchElementException("No such genre with id: %s".formatted(id));
                        }
                );
    }
}
