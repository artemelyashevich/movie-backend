package com.example.demo.service.implementation;

import com.example.demo.entity.user.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return Optional.ofNullable(this.userRepository.findByUsername(username)
                .orElseThrow(NoSuchElementException::new));
    }

    @Override
    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return Optional.ofNullable(this.userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new));
    }
}