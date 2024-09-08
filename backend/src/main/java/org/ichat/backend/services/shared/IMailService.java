package org.ichat.backend.services.shared;

import org.ichat.backend.model.util.MailType;

import java.util.concurrent.CompletableFuture;

public interface IMailService {
    /**
     * Send an email to the given email address. The process is asynchronous to avoid blocking the main thread. <br>
     * It uses the Thymeleaf template engine to generate the email content (located in resources/templates). <br>
     *
     * @param to     destination email
     * @param header email header
     * @param code   a code to be sent
     * @param type   type of email.
     * @return
     * @see MailType
     */
    void sendMail(String to, String header, String code, MailType type);
}
