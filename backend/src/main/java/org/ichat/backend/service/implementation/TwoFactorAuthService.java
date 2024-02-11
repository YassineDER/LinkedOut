package org.ichat.backend.service.implementation;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.*;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.samstevens.totp.util.Utils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.service.ITwoFactorAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TwoFactorAuthService implements ITwoFactorAuthService {
    @Value("${spring.application.name}")
    private String app_name;

    @Override
    public boolean codeIsValid(String secret, String provided_code) {
        TimeProvider timeProvider = new SystemTimeProvider();
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        CodeVerifier codeVerifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        return codeVerifier.isValidCode(secret, provided_code);
    }

    @Override
    public String generateMfaSecret() {
        return new DefaultSecretGenerator().generate();
    }

    @Override
    public String generateMfaImage(String secret, String email) throws QrGenerationException {
        QrData data = new QrData.Builder()
                .label(email)
                .secret(secret)
                .issuer(app_name)
                .algorithm(HashingAlgorithm.SHA1)
                .digits(6)
                .period(30)
                .build();

        QrGenerator generator = new ZxingPngQrGenerator();
        byte[] imageData = generator.generate(data);
        return Utils.getDataUriForImage(imageData, generator.getImageMimeType());
    }
}
