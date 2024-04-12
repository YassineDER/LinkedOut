package org.ichat.backend.model.util;

import lombok.Getter;

@Getter
public enum MailType {
    RESET_PASSWORD("Reset your password - Securecapita"),
    VERIFY_ACCOUNT("Verify your account - Securecapita");

    private final String label;

    MailType(String label) {
        this.label = label;
    }
}