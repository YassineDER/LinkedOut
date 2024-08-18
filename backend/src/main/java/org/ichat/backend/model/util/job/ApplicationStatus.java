package org.ichat.backend.model.util.job;

/**
 * ApplicationStatus is an enum that represents the status of a job application.
 * @see org.ichat.backend.model.tables.jobs.JobApplication
 */
public enum ApplicationStatus {
    PENDING,
    ACCEPTED,
    REJECTED;

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
