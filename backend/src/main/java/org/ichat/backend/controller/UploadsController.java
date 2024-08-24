package org.ichat.backend.controller;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.storage.StorageResponseDTO;
import org.ichat.backend.services.shared.IStorageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling file uploads and downloads from Oracle Cloud Storage
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storage")
public class UploadsController {
    private final IStorageService storageService;

    @GetMapping("/generate-par")
    public ResponseEntity<StorageResponseDTO> getFile(User me) {
        String PAR_URL = storageService.createPreAuthenticatedRequest(me, "user-assets");
        return ResponseEntity.ok(new StorageResponseDTO(PAR_URL));
    }

}
