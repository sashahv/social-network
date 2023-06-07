package com.olekhv.socialnetwork.model.attachment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;
import java.util.Objects;

@Document(collection = "largeFile")
@Getter
@Setter
@NoArgsConstructor
public class LargeFile extends Attachment {
    private InputStream stream;

    public LargeFile(String id, String name, InputStream stream) {
        super(id, name);
        this.stream = stream;
    }

    public LargeFile(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LargeFile largeFile = (LargeFile) o;
        return stream.equals(largeFile.stream);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stream);
    }
}
