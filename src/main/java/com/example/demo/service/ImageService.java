package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String upload(MultipartFile file) throws IOException;

    byte[] getByName(String name) throws IOException;

    void deleteByName(String name);
}
