package org.ichat.backend.service.shared.implementation;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.AccountException;
import org.ichat.backend.model.util.MailType;
import org.ichat.backend.service.shared.IMailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MailService implements IMailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.timeout}")
    private int MAIL_TIMEOUT;
    @Value("${spring.mail.from}")
    private String FROM;

    @Override
    @Async
    public void sendMail(String to, String header, String code, MailType type) {
        Context context = new Context();
        context.setVariables(Map.of(
                "header", header,
                "title", type.getLabel(),
                "email", to,
                "code", code));

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(to);
            helper.setSubject(header);
            helper.setFrom(FROM);
            String templateFile = switch (type) {
                case RESET_PASSWORD -> "reset";
                case VERIFY_ACCOUNT -> "email";
            };

            String mailContent = templateEngine.process(templateFile, context);
            helper.setText(mailContent, true);

            CompletableFuture.runAsync(() -> mailSender.send(mimeMessage))
                    .get(MAIL_TIMEOUT, TimeUnit.SECONDS);

        } catch (MailException e) {
            throw new AccountException("Failed to send reset email. The operation timed out.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to send reset email. " + e.getMessage());
        }
    }
}
