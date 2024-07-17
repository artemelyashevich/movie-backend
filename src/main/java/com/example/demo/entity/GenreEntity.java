package com.example.demo.entity;

import com.example.demo.entity.contract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreEntity extends AbstractEntity {

    @TextIndexed
    private String details;

    @Indexed(unique = true)
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(details, that.details) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), details, title);
    }
}
