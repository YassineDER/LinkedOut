package org.ichat.backend.services.shared;

import org.ichat.backend.model.util.StorageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IStorageService {
    void init() throws IOException;

    StorageFile store(MultipartFile file) throws IOException;
    StorageFile getImage(String filename) throws IOException;
}
