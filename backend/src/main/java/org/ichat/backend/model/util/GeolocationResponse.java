package org.ichat.backend.model.util;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class GeolocationResponse {
    private String query, city, regionName, country, zip, timezone;
}
