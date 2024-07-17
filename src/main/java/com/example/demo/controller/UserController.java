package com.example.demo.controller;

import com.example.demo.dto.auth.SignUpDto;
import com.example.demo.entity.user.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.util.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        return ResponseEntity
                .ok()
                .body(this.userService.findAll());
    }

    @PatchMapping("{userId}")
    public ResponseEntity<Void> update(
            final @PathVariable("userId") String userId,
            final @Valid SignUpDto dto,
            final BindingResult bindingResult
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        this.userService.update(userId, dto);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserEntity> getById(final @PathVariable("userId") String id) {
        return ResponseEntity
                .ok()
                .body(this.userService.findById(id));
    }
}
