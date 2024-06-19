package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String title;
}
