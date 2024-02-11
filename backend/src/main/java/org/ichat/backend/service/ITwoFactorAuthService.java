package org.ichat.backend.service;

import dev.samstevens.totp.exceptions.QrGenerationException;

public interface ITwoFactorAuthService {
    boolean codeIsValid(String secret, String provided_code);
    String generateMfaSecret();
    String generateMfaImage(String secret, String email) throws QrGenerationException;
}
