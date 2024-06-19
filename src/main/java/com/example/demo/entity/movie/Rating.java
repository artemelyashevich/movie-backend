package com.example.demo.entity.movie;

import lombok.Getter;

@Getter
public enum Rating {

    BRONZE(1),
    SILVER(2),
    GOLD(3),
    PLATINUM(4),
    DIAMOND(5);

    private final Integer status;

    Rating(Integer status) {
        this.status = status;
    }
}
