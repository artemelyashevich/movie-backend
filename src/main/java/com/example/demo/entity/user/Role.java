package com.example.demo.entity.user;

public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String name;

    Role(String name) {
        this.name  = name;
    }
}