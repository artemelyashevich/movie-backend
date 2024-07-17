package com.example.demo.service.implementation;

import com.example.demo.entity.ImageFile;
import com.example.demo.repository.ImageRepository;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Value("${application.uploads.folder-path}")
    private String PATH_TO_FOLDER;

    @Override
    public String upload(final MultipartFile file) throws IOException {
        final String filePath = this.PATH_TO_FOLDER.concat(
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"))
                        .concat(
                                Objects.requireNonNull(file.getOriginalFilename())
                        )
        );
        final ImageFile imageFile = this.imageRepository.save(ImageFile
                .builder()
                .name(
                        LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("ddMMyyHHmmss-"))
                                .concat(file.getName()))
                .filePath(filePath)
                .fileType(file.getContentType())
                .build());
        file.transferTo(new File(filePath));
        return imageFile.getName();
    }

    @Override
    public byte[] getByName(final String name) throws IOException {
        final ImageFile file = this.imageRepository.findByName(name)
                .orElseThrow(() ->
                        new NoSuchElementException("No such file with name: %s".formatted(name))
                );
        return Files.readAllBytes(new File(file.getFilePath()).toPath());
    }

    @Override
    public void deleteByName(final String name) {
        this.imageRepository.findByName(name)
                .ifPresentOrElse(
                        this.imageRepository::delete,
                        () -> {
                            throw new NoSuchElementException("No such image with name: %s".formatted(name));
                        }
                );
    }
}
