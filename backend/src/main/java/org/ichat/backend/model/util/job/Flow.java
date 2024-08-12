package org.ichat.backend.model.util.job;

public enum Flow {
    HYBRID,
    REMOTE,
    ONSITE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
