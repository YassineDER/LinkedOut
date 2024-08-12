package org.ichat.backend.model.util;

public enum RoleType {
    ADMIN,
    JOBSEEKER,
    COMPANY;

    @Override
    public String toString() {
        return name().toUpperCase();
    }

}
