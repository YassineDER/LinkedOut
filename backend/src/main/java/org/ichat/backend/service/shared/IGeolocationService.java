package org.ichat.backend.service.shared;

import org.ichat.backend.model.util.GeolocationResponse;

public interface IGeolocationService {
    GeolocationResponse getGeolocationFromIP(String ip);

}
