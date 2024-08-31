package org.ichat.backend.model.util.storage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * StorageFile is a data transfer object that is used to send a file to the client.
 */
@Data
@AllArgsConstructor
public class StorageResponseDTO {
    private String PAR_URL;
    private LocalDateTime expires;

}
