package com.example.demo.service;

import com.example.demo.entity.user.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(String id);
}
