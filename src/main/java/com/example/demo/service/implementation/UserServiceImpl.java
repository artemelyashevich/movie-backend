package com.example.demo.service.implementation;

import com.example.demo.dto.auth.SignUpDto;
import com.example.demo.entity.user.Role;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.exception.BadRequestException;
import com.example.demo.mapper.DtoMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DtoMapper<SignUpDto, UserEntity> dtoMapper;
    private final PasswordEncoder passwordEncoder;

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

    @Override
    public UserEntity create(SignUpDto dto) {
        UserEntity user = this.dtoMapper.convertFromDto(dto);
        user.setPassword(this.passwordEncoder.encode(dto.password()));
        user.setRoles(
                List.of(Role.ROLE_USER)
        );
        return this.userRepository.save(user);
    }
}