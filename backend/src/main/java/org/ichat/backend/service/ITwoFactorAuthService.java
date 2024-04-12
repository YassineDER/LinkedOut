package org.ichat.backend.service;

import dev.samstevens.totp.exceptions.QrGenerationException;
import org.springframework.lang.NonNull;

public interface ITwoFactorAuthService {
    boolean codeIsValid(String secret, @NonNull String provided_code);
    String generateMfaSecret();
    String generateMfaImage(String secret, String email) throws QrGenerationException;
}
