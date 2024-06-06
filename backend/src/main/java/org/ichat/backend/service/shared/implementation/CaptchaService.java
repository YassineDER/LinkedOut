package org.ichat.backend.service.shared.implementation;

import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.util.auth.RecaptchaResponse;
import org.ichat.backend.service.shared.ICaptchaService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CaptchaService implements ICaptchaService {
    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";
    private static final String SECRET = System.getenv("RECAPTCHA_SECRET");

    @Override
    public RecaptchaResponse verifyCaptcha(String token) {
        RestClient client = RestClient.create();

        var response = client.post()
                .uri(CAPTCHA_URL + "?secret=" + SECRET + "&response=" + token)
                .retrieve().toEntity(RecaptchaResponse.class);

        return response.getBody();
    }

}
