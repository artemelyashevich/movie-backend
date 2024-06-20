package com.example.demo.controller;

import com.example.demo.dto.auth.AuthResponseDto;
import com.example.demo.dto.auth.SignInDto;
import com.example.demo.dto.auth.SignUpDto;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import com.example.demo.util.Utils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jdk.jshell.execution.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("sign-in")
    public ResponseEntity<AuthResponseDto> signIn(
            @Valid @RequestBody SignInDto dto,
            BindingResult bindingResult,
            UriComponentsBuilder uriComponentsBuilder
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        final UserEntity user = this.userService.findByUsername(dto.username())
                .orElseThrow(NoSuchElementException::new);
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .replacePath("users/{userId}")
                                .build(
                                        Map.of("userId", user.getId())
                                )
                )
                .body(
                        this.authService.signIn(dto)
                );
    }

    @PostMapping("sing-up")
    public ResponseEntity<AuthResponseDto> signUp(
            @Valid @RequestBody SignUpDto dto,
            BindingResult bindingResult,
            UriComponentsBuilder uriComponentsBuilder,
            HttpServletResponse response
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        final UserEntity user = this.userService.create(dto);
        final AuthResponseDto data = this.authService.signUp(dto);
        Cookie jwtCookie = new Cookie("JWT", data.token());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        response.addCookie(jwtCookie);
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .replacePath("users/{userId}")
                                .build(
                                        Map.of("userId", user.getId())
                                )
                )
                .body(
                        data
                );
    }
}
