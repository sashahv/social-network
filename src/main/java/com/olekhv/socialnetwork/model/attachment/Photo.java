package com.olekhv.socialnetwork.model.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "photo")
@Getter
@Setter
@NoArgsConstructor
public class Photo extends Attachment {
    private Binary image;

    public Photo(String id, String name, Binary image) {
        super(id, name);
        this.image = image;
    }

    public Photo(Binary image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Photo photo = (Photo) o;
        return image.equals(photo.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), image);
    }
}
