package com.example.demo.entity.movie;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.GenreEntity;
import com.example.demo.entity.contract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;


@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity extends AbstractEntity {

    @Indexed(unique = true)
    private String title;

    @TextIndexed
    private String description;

    private Rating rating;

    @TextIndexed
    private String duration;

    @TextIndexed
    private String watchLink;

    private Quality quality;

    private Status status;

    private Integer releaseYear;

    @TextIndexed
    private List<String> languages;

    @TextIndexed
    private String subtitle;

    @TextIndexed
    private String bannerImgUrl;

    @TextIndexed
    private String imgUrl;

    private Size size;

    @DBRef
    private List<GenreEntity> genres;

    @DBRef
    private List<CategoryEntity> categories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity movie = (MovieEntity) o;
        return Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && rating == movie.rating && Objects.equals(duration, movie.duration) && Objects.equals(watchLink, movie.watchLink) && quality == movie.quality && status == movie.status && Objects.equals(releaseYear, movie.releaseYear) && Objects.equals(languages, movie.languages) && Objects.equals(subtitle, movie.subtitle) && Objects.equals(bannerImgUrl, movie.bannerImgUrl) && Objects.equals(imgUrl, movie.imgUrl) && size == movie.size && Objects.equals(genres, movie.genres) && Objects.equals(categories, movie.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, rating, duration, watchLink, quality, status, releaseYear, languages, subtitle, bannerImgUrl, imgUrl, size, genres, categories);
    }
}
