package org.ichat.backend.service.implementation;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.*;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.samstevens.totp.util.Utils;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.ichat.backend.service.ITwoFactorAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TwoFactorAuthService implements ITwoFactorAuthService {
    @Value("${spring.application.name}")
    private String app_name;
    @Value("${totp.secret.length}")
    private int SECRET_LENGTH;
    @Value("${totp.time.period}")
    private int TIME_PERIOD;

    @Override
    public boolean codeIsValid(String secret, @NonNull String provided_code) {
        TimeProvider timeProvider = new SystemTimeProvider();
        CodeGenerator codeGenerator = new DefaultCodeGenerator(HashingAlgorithm.SHA256);
        CodeVerifier codeVerifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
        return codeVerifier.isValidCode(secret, provided_code);
    }

    @Override
    public String generateMfaSecret() {
        return new DefaultSecretGenerator(SECRET_LENGTH).generate();
    }

    @Override
    public String generateMfaImage(String secret, String email) throws QrGenerationException {
        QrData data = new QrData.Builder()
                .label(email)
                .secret(secret)
                .issuer(app_name)
                .algorithm(HashingAlgorithm.SHA256)
                .digits(6)
                .period(TIME_PERIOD)
                .build();

        QrGenerator generator = new ZxingPngQrGenerator();
        byte[] imageData = generator.generate(data);
        return Utils.getDataUriForImage(imageData, generator.getImageMimeType());
    }
}
