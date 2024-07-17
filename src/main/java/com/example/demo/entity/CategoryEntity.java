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

@Document(collection = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryEntity extends AbstractEntity {

    @Indexed(unique = true)
    private String title;

    @TextIndexed
    private String details;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity category = (CategoryEntity) o;
        return Objects.equals(getId(), category.getId()) && Objects.equals(title, category.title) && Objects.equals(details, category.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), title, details);
    }
}
