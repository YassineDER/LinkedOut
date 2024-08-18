package org.ichat.backend.services.shared;

import org.ichat.backend.model.util.StorageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IStorageService {
    /**
     * Initialize the storage service from the config file <br>
     * By default, the config file is located at ~/.oci/config
     * @throws IOException if the config file cannot be read
     */
    void init() throws IOException;

    /**
     * Store the file in the storage service
     * @param file the file to store (MultipartFile)
     * @return the stored file
     * @throws IOException if the file cannot be stored
     * @see StorageFile
     */
    StorageFile store(MultipartFile file) throws IOException;

    /**
     * Get the image from the storage service
     * @param filename the name of the file to get
     * @return the file
     * @throws IOException if the file cannot be retrieved
     * @see StorageFile
     */
    StorageFile getImage(String filename) throws IOException;
}
