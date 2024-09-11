package org.ichat.backend.services.shared;

import org.ichat.backend.exception.StorageException;
import org.ichat.backend.model.util.storage.StorageResponseDTO;
import org.ichat.backend.services.account.IUserService;
import org.ichat.backend.services.social.IPostService;

import java.time.OffsetDateTime;

public interface IStorageService {

    /**
     * Create a pre-authenticated request for a user to access a bucket (Valid for 24 hours by default).
     *
     * @param bucketName the name of the bucket
     * @return the pre-authenticated request URL
     */
    StorageResponseDTO createPreAuthenticatedRequest(String bucketName);

    void uploadBase64Image(String base64, String objectNamePath) throws StorageException;

    /**
     * Upload an image from a URL to a bucket.
     *
     * @param url        the URL of the image
     * @param bucketName the name of the bucket
     * @param objectName the name of the object (or its path) in the bucket
     */
    void uploadImageFromUrl(String url, String bucketName, String objectName);

    /**
     * Delete unused profile images from the user-assets bucket.
     *
     * @param userService the user service to check for unused images. Must be injected to avoid circular dependencies.
     * @throws StorageException if an error occurs while deleting the images
     */
    void deleteUnusedUserImages(IUserService userService) throws StorageException;

    void deleteUnusedPostsImages(IPostService postService);

    /**
     * Delete expired images from a bucket.
     * @param threshold the threshold date to delete images before
     */
    void deleteExpiredPARs(OffsetDateTime threshold);
}
