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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
            @PathVariable("userId") String userId,
            @Valid SignUpDto dto,
            BindingResult bindingResult
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        this.userService.update(userId, dto);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserEntity> getById(@PathVariable("userId") String id) {
        return ResponseEntity
                .ok()
                .body(this.userService.findById(id).orElseThrow(
                        NoSuchElementException::new
                ));
    }
}
