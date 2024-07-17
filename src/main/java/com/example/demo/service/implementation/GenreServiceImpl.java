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
    public Optional<GenreEntity> findById(String id) {
        return Optional.ofNullable(this.genreRepository.findById(id)
                .orElseThrow(NoSuchElementException::new));
    }

    @Override
    public Optional<GenreEntity> findByName(String name) {
        return Optional.ofNullable(
                this.genreRepository.findByTitle(name).orElseThrow(NoSuchElementException::new)
        );
    }

    @Override
    public GenreEntity create(GenreDto dto) {
        return this.genreRepository.save(this.genreMapper.convertFromDto(dto));
    }

    @Override
    public void update(String id, GenreDto dto) {
        this.genreRepository.findById(id)
                .ifPresentOrElse(
                        genre -> {
                            genre.setTitle(dto.title());
                            genreRepository.save(genre);
                        },
                        () -> {
                            throw new NoSuchElementException();
                        }
                );
    }

    @Override
    public void delete(String id) {
        this.genreRepository.findById(id)
                .ifPresentOrElse(
                        genreRepository::delete,
                        () -> {
                            throw new NoSuchElementException();
                        }
                );
    }
}
