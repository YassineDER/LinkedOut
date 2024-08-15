package org.ichat.backend.services.shared.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.util.GeolocationResponseDTO;
import org.ichat.backend.services.shared.IGeolocationService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GeolocationService implements IGeolocationService {
    private static final String IP_API_URL = "http://ip-api.com/json/";
    private final RestClient http = RestClient.create();

    @Override
    public GeolocationResponseDTO getGeolocationFromIP(String ip) {
        if (ip == null || ip.isEmpty())
            return null;

        String url = UriComponentsBuilder.fromHttpUrl(IP_API_URL)
                .pathSegment(ip)
                .toUriString();

        return http.get()
                .uri(url)
                .retrieve()
                .body(GeolocationResponseDTO.class);
    }

}
