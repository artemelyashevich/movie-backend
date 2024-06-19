package com.example.demo.entity.movie;

import lombok.Getter;

@Getter
public enum Size {

    MIN("350mb"),
    LOW("1gb"),
    MIDDLE("2gb"),
    HIGH("4gb");

    private final String size;

    Size(String size) {
        this.size = size;
    }
}
