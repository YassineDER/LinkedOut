package org.ichat.backend.service;

import org.ichat.backend.model.util.auth.RecaptchaResponse;

public interface ICaptchaService {
    RecaptchaResponse verifyCaptcha(String token);
}
