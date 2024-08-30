package org.ichat.backend.services.shared;

import org.ichat.backend.exception.StorageException;
import org.ichat.backend.model.util.storage.StorageResponseDTO;

import java.time.OffsetDateTime;

public interface IStorageService {

    /**
     * Create a pre-authenticated request for a user to access a bucket (Valid for 24 hours by default).
     *
     * @param bucketName the name of the bucket
     * @return the pre-authenticated request URL
     */
    StorageResponseDTO createPreAuthenticatedRequest(String bucketName);

    /**
     * Upload an image from a URL to a bucket.
     *
     * @param url        the URL of the image
     * @param bucketName the name of the bucket
     * @param objectName the name of the object (or its path) in the bucket
     */
    void uploadImageFromUrl(String url, String bucketName, String objectName);

    void deleteUnusedImages(String bucketName, String objectNamePath) throws StorageException;

    /**
     * Delete expired images from a bucket.
     * @param threshold the threshold date to delete images before
     */
    void deleteExpiredPARs(OffsetDateTime threshold);
}
