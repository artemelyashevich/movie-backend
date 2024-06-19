package com.example.demo.entity.movie;

import lombok.Getter;

@Getter
public enum Quality {
    LOW("140P"),
    BAD("240P"),
    NORMAL("480P"),
    HIGH("720P"),
    ULTRA("4K");

    private final String status;

    Quality(String status) {
        this.status = status;
    }
}
