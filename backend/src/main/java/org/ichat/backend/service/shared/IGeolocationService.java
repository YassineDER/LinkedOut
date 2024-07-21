package org.ichat.backend.service.shared;

import org.ichat.backend.model.util.GeolocationResponseDTO;

public interface IGeolocationService {
    GeolocationResponseDTO getGeolocationFromIP(String ip);

}
