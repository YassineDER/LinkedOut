package org.ichat.backend.service.shared.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.util.GeolocationResponse;
import org.ichat.backend.service.shared.IGeolocationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GeolocationService implements IGeolocationService {
    private static final String IPINFO_URL = "http://ip-api.com/json/";
    private final RestClient http = RestClient.create();

    @Override
    public GeolocationResponse getGeolocationFromIP(String ip) {
        if (ip == null || ip.isEmpty())
            return null;

        String url = UriComponentsBuilder.fromHttpUrl(IPINFO_URL)
                .pathSegment(ip)
                .toUriString();

        return http.get()
                .uri(url)
                .retrieve()
                .body(GeolocationResponse.class);
    }

}
