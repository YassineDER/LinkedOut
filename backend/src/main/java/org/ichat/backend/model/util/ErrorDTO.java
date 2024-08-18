package org.ichat.backend.model.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * ErrorDTO is a data transfer object that is used to send an error response to the client.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private String error, cause, type;
    @JsonProperty("class")
    private String className;
}
