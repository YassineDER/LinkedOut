package org.ichat.backend.model.util;

import lombok.Getter;

/**
 * MailType is an enum that represents the type of mail that is being sent.
 */
@Getter
public enum MailType {
    RESET_PASSWORD("Renitialiser votre mot de passe - LinkedOut"),
    VERIFY_ACCOUNT("Verifier votre compte - LinkedOut"),
    MFA("Code de vérification à deux facteurs - LinkedOut");

    private final String label;

    MailType(String label) {
        this.label = label;
    }
}