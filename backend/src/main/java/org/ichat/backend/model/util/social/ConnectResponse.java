package org.ichat.backend.model.util.social;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ichat.backend.model.tables.social.Connection;

@Data
@AllArgsConstructor
public class ConnectResponse {
    private Connection connection;
    private boolean success;
}
