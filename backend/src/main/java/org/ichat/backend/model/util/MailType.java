package org.ichat.backend.model.util;

import lombok.Getter;

@Getter
public enum MailType {
    RESET_PASSWORD("Reset your password - LinkedOut"),
    VERIFY_ACCOUNT("Verify your account - LinkedOut");

    private final String label;

    MailType(String label) {
        this.label = label;
    }
}