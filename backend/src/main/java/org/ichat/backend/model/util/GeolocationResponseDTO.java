package org.ichat.backend.model.util;

import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * .GeolocationResponseDTO is a data transfer object that is used to fetch geolocation data based on IP address.
 */
@Data
@AllArgsConstructor
public class GeolocationResponseDTO {
    private String query, city, regionName, country, zip, timezone;
}
