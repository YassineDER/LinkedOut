package org.ichat.backend.service;

import org.ichat.backend.model.util.MailType;

import java.net.UnknownHostException;

public interface IMailService {
    void sendMail(String to, String header, String code, MailType type);
}
