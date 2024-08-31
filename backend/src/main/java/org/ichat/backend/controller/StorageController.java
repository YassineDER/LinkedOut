package org.ichat.backend.controller;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.util.storage.StorageResponseDTO;
import org.ichat.backend.services.account.IUserService;
import org.ichat.backend.services.shared.IStorageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling file uploads and downloads from Oracle Cloud Storage
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storage")
public class StorageController {
    private final IStorageService storageService;
    private final IUserService userService;

    @GetMapping("/generate-par")
    public ResponseEntity<StorageResponseDTO> getFile() {
        StorageResponseDTO PAR = storageService.createPreAuthenticatedRequest("user-assets");
        return ResponseEntity.ok(PAR);
    }

}
