package com.example.demo.service;

import com.example.demo.dto.auth.AuthResponseDto;
import com.example.demo.dto.auth.SignInDto;
import com.example.demo.dto.auth.SignUpDto;

public interface AuthService {

    AuthResponseDto signIn(SignInDto dto);

    AuthResponseDto signUp(SignUpDto dto);
}
