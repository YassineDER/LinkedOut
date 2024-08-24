package org.ichat.backend.services.shared;

import org.ichat.backend.model.tables.User;

import java.time.OffsetDateTime;

public interface IStorageService {

    /**
     * Create a pre-authenticated request for a user to access a bucket (Valid for 24 hours by default).
     * @param user the user that will access the bucket
     * @param bucketName the name of the bucket
     * @return the pre-authenticated request URL
     */
    String createPreAuthenticatedRequest(User user, String bucketName);

    /**
     * Upload an image from a URL to a bucket.
     * @param url the URL of the image
     * @param bucketName the name of the bucket
     * @param objectName the name of the object (or its path) in the bucket
     * @return true if the image was uploaded successfully, false otherwise
     */
    boolean uploadImageFromUrl(String url, String bucketName, String objectName);

    /**
     * Delete expired images from a bucket.
     * @param threshold the threshold date to delete images before
     */
    void deleteExpiredImages(OffsetDateTime threshold);
}
