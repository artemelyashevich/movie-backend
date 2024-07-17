package com.example.demo.service.implementation;

import com.example.demo.dto.auth.SignUpDto;
import com.example.demo.entity.user.Role;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.mapper.contract.DtoMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DtoMapper<SignUpDto, UserEntity> dtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity findByUsername(final String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("No such user with username: %s".formatted(username)));
    }

    @Override
    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public UserEntity findById(final String id) {
        return this.userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public UserEntity create(final SignUpDto dto) {
        UserEntity user = this.dtoMapper.convertFromDto(dto);
        user.setPassword(this.passwordEncoder.encode(dto.password()));
        user.setRoles(
                List.of(Role.ROLE_USER)
        );
        return this.userRepository.save(user);
    }

    @Override
    public void update(final String userId, final SignUpDto dto) {
        final UserEntity user = this.findById(userId);
        user.setEmail(dto.email());
        user.setUsername(dto.username());
        this.userRepository.save(user);
    }

    @Override
    public void delete(final String username, final String password) {
        final UserEntity user = this.findByUsername(username);
        this.userRepository.delete(user);
    }
}