package com.example.demo.mapper;

import com.example.demo.dto.auth.SignUpDto;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.mapper.contract.DtoMapper;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements DtoMapper<SignUpDto, UserEntity> {

    @NonNull
    @Override
    public UserEntity convertFromDto(SignUpDto dto) {
        return UserEntity.builder()
                .username(dto.username())
                .email(dto.email())
                .build();
    }

    @Override
    public UserEntity update(UserEntity entity, SignUpDto dto) {
        return null;
    }
}
