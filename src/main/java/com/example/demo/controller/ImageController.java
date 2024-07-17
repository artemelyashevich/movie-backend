package com.example.demo.controller;

import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping(value = "{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getByName(final @PathVariable("imageName") String name) throws IOException {
        return ResponseEntity
                .ok()
                .body(this.imageService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<String> create(
            final @RequestParam MultipartFile file,
            final UriComponentsBuilder uriComponentsBuilder
    ) throws IOException {
        final String name = this.imageService.upload(file);
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .replacePath("/api/v1/images/{imageName}")
                                .build(Map.of("imageName", name))
                )
                .build();
    }

    @DeleteMapping("{imageName}")
    public ResponseEntity<Void> remove(final @PathVariable("imageName") String name) {
        this.imageService.deleteByName(name);
        return ResponseEntity
                .noContent()
                .build();
    }
}

