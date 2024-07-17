package com.example.demo.entity;

import com.example.demo.entity.contract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageFile extends AbstractEntity {

    @TextIndexed
    private String name;

    @TextIndexed
    private String filePath;

    @TextIndexed
    private String fileType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageFile imageFile = (ImageFile) o;
        return Objects.equals(name, imageFile.name) && Objects.equals(filePath, imageFile.filePath) && Objects.equals(fileType, imageFile.fileType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, filePath, fileType);
    }
}
