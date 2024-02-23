package org.ichat.backend.model.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageFile {
    private String name;
    private MediaType mediaType;
    private byte[] data;

    public StorageFile(String name, byte[] data) {
        this.name = name.toLowerCase();
        String extension = name.toLowerCase().substring(name.lastIndexOf(".") + 1);
        this.mediaType = switch (extension) {
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
            case "png" -> MediaType.IMAGE_PNG;
            case "gif" -> MediaType.IMAGE_GIF;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
        this.data = data;
    }
}
