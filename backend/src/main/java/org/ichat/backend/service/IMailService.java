package org.ichat.backend.service;

import org.ichat.backend.model.util.MailType;

public interface IMailService {
    void sendMail(String to, String header, String code, MailType type) throws Exception;
}
