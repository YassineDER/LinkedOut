package org.ichat.backend.model.util;

/**
 * RoleType is an enum that represents the different types of roles that a user can have.
 */
public enum RoleType {
    ADMIN,
    JOBSEEKER,
    COMPANY;

    @Override
    public String toString() {
        return name().toUpperCase();
    }

}
