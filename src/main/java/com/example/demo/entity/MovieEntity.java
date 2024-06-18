package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity {


    @Id
    private String id;

    private String title;

    private String description;

    private Integer rating;

    private String duration;

    private String watchLink;

    private String quality;

    private String status;

    private String releaseYear;

    private String language;

    private String subtitle;

    private Integer size;

    private String categoryTitle;
}
