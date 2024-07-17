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
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("sign-in")
    public ResponseEntity<AuthResponseDto> signIn(
            final @Valid @RequestBody SignInDto dto,
            final BindingResult bindingResult,
            final UriComponentsBuilder uriComponentsBuilder
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        final UserEntity user = this.userService.findByUsername(dto.username());
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .replacePath("/api/v1/users/{userId}")
                                .build(
                                        Map.of("userId", user.getId())
                                )
                )
                .body(
                        this.authService.signIn(dto)
                );
    }

    @PostMapping("sign-up")
    public ResponseEntity<AuthResponseDto> signUp(
            final @Valid @RequestBody SignUpDto dto,
            final BindingResult bindingResult,
            final UriComponentsBuilder uriComponentsBuilder,
            final HttpServletResponse response
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
                                .replacePath("/api/v1/users/{userId}")
                                .build(
                                        Map.of("userId", user.getId())
                                )
                )
                .body(
                        data
                );
    }
}
