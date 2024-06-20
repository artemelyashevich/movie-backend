package com.example.demo.service.implementation;

import com.example.demo.dto.auth.AuthResponseDto;
import com.example.demo.dto.auth.SignInDto;
import com.example.demo.dto.auth.SignUpDto;
import com.example.demo.security.UserDetailsServiceImpl;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import com.example.demo.util.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsServiceImpl userService;
    private final UserService customUserService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto signIn(SignInDto dto) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password())
        );
        return new AuthResponseDto(
                Token.generateToken(
                        this.userService.loadUserByUsername(dto.username())
                )
        );
    }

    @Override
    public AuthResponseDto signUp(SignUpDto dto) {
        return new AuthResponseDto(
                Token.generateToken(userService.loadUserByUsername(dto.username())
                )
        );
    }
}
