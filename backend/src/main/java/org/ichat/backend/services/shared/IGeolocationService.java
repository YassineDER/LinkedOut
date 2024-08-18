package org.ichat.backend.services.shared;

import org.ichat.backend.model.util.GeolocationResponseDTO;

public interface IGeolocationService {
    /**
     * Get the geolocation of an IP address.
     * @param ip the IP address
     * @return the geolocation of the IP address
     *
     * @see GeolocationResponseDTO for the data structure of the response
     */
    GeolocationResponseDTO getGeolocationFromIP(String ip);

}
