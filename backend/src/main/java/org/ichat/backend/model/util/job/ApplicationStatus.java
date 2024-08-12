package org.ichat.backend.model.util.job;

public enum ApplicationStatus {
    PENDING,
    ACCEPTED,
    REJECTED;

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
