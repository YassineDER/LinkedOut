package org.ichat.backend.controller;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.util.StorageFile;
import org.ichat.backend.services.shared.IStorageService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller for handling file uploads and downloads from Oracle Cloud Storage
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
public class UploadsController {
    private final IStorageService storageService;

    /**
     * Get a file by its name from the storage bucket
     * @param filename the name of the file
     * @return the file as a byte array
     * @throws IOException if the file is not found
     */
    @GetMapping(value = "/files/{filename:.+}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> getFile(@PathVariable String filename) throws IOException {
        storageService.init();
        StorageFile file = storageService.getImage(filename.toLowerCase());
        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(file.getMediaType())
                .body(file.getData());
    }

    /**
     * Clear the cache of images
     * @return a response entity with a message
     */
    @GetMapping("/cache/clear")
    @CacheEvict(value = "images", allEntries = true)
    public ResponseEntity<String> clearCache() {
        return ResponseEntity.ok("Cache cleared");
    }

    /**
     * Upload an image to the storage bucket
     * @param file the image file
     * @return a response entity with the image as a byte array
     * @throws IOException if the file is not found
     */
    @PostMapping("/")
    public ResponseEntity<byte[]> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        storageService.init();
        StorageFile uploaded = storageService.store(file);
        return ResponseEntity.ok()
                .contentType(uploaded.getMediaType())
                .body(uploaded.getData());
    }

}
