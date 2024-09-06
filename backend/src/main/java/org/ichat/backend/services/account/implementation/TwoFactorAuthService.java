package org.ichat.backend.services.account.implementation;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.*;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.samstevens.totp.util.Utils;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.MailType;
import org.ichat.backend.services.account.IAccountVerificationService;
import org.ichat.backend.services.account.ITwoFactorAuthService;
import org.ichat.backend.services.shared.IMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TwoFactorAuthService implements ITwoFactorAuthService {
    private final IAccountVerificationService verificationService;
    private final IMailService mailService;

    @Value("${spring.application.name}")
    private String app_name;
    @Value("${totp.secret.length}")
    private int SECRET_LENGTH;
    @Value("${totp.time.period}")
    private int TIME_PERIOD;

    @Override
    public String requestMfa(User user) {
        int number = (int) (Math.random() * 1000000);
        String code = String.format("%06d", number);
        mailService.sendMail(user.getEmail(), "Activation du double facteur d'authentification", code, MailType.MFA);
        verificationService.saveVerificationRequest(user, code);
        return "Un code a été envoyé à " + user.getEmail() + ". Veuillez vérifier votre boîte de réception";
    }

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
