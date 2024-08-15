package org.ichat.backend.services.shared;

import org.ichat.backend.model.util.GeolocationResponseDTO;

public interface IGeolocationService {
    GeolocationResponseDTO getGeolocationFromIP(String ip);

}
