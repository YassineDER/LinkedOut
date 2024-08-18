package org.ichat.backend.model.util.job;

/**
 * Flow is an enum that represents the flow of a job.
 * @see org.ichat.backend.model.tables.jobs.Job
 */
public enum Flow {
    HYBRID,
    REMOTE,
    ONSITE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
