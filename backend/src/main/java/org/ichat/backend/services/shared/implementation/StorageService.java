package org.ichat.backend.services.shared.implementation;

import com.oracle.bmc.objectstorage.model.CreatePreauthenticatedRequestDetails;
import com.oracle.bmc.objectstorage.model.PreauthenticatedRequest;
import com.oracle.bmc.objectstorage.model.PreauthenticatedRequestSummary;
import com.oracle.bmc.objectstorage.requests.CreatePreauthenticatedRequestRequest;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.DeletePreauthenticatedRequestRequest;
import com.oracle.bmc.objectstorage.requests.ListPreauthenticatedRequestsRequest;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import com.oracle.bmc.objectstorage.responses.ListPreauthenticatedRequestsResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.StorageException;
import org.ichat.backend.model.tables.User;
import org.ichat.backend.model.util.RoleType;
import org.ichat.backend.services.shared.IStorageService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class StorageService implements IStorageService {
    private final ObjectStorageClient client;

    @Override
    public String createPreAuthenticatedRequest(User user, String bucketName) throws StorageException {
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4); // 2 hours

        // TODO: remove this line after test
        if (user.getRole().getName().equals(RoleType.ADMIN.name()))
            expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 5); // 5 minutes for debugging

        try {
            CreatePreauthenticatedRequestDetails details = CreatePreauthenticatedRequestDetails.builder()
                    .name(user.getUser_id() + "-" + LocalDateTime.now())
                    .accessType(CreatePreauthenticatedRequestDetails.AccessType.AnyObjectReadWrite)
                    .bucketListingAction(PreauthenticatedRequest.BucketListingAction.Deny)
                    .timeExpires(expiration)
                    .build();

            CreatePreauthenticatedRequestRequest request = CreatePreauthenticatedRequestRequest.builder()
                    .namespaceName("ax0judwwk3y8")
                    .bucketName(bucketName)
                    .createPreauthenticatedRequestDetails(details)
                    .build();

            return client.createPreauthenticatedRequest(request)
                    .getPreauthenticatedRequest().getAccessUri();
        } catch (Exception e) {
            throw new StorageException("Failed to create pre-authenticated request", e);
        }
    }

    @Override
    public boolean uploadImageFromUrl(String url, String bucketName, String objectNamePath) throws StorageException {
        try (InputStream IS = new URL(url).openStream()) {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .namespaceName("ax0judwwk3y8")
                    .bucketName(bucketName)
                    .objectName(objectNamePath)
                    .contentType(MediaType.IMAGE_PNG_VALUE)
                    .putObjectBody(IS)
                    .build();
            client.putObject(objectRequest);
            return true;
        } catch (Exception e) {
            throw new StorageException("Failed to store image", e);
        }
    }


    @Override
    public void deleteExpiredImages(OffsetDateTime threshold) throws StorageException {
        try {
            ListPreauthenticatedRequestsRequest listPreauthenticatedRequestsRequest = ListPreauthenticatedRequestsRequest.builder()
                    .namespaceName("ax0judwwk3y8")
                    .bucketName("user-assets")
                    .limit(50)
                    .build();
            ListPreauthenticatedRequestsResponse list =
                    client.listPreauthenticatedRequests(listPreauthenticatedRequestsRequest);

            // get the list of the expired pre-authenticated requests as ids
            var L = list.getItems().stream()
                    .filter(req -> req.getTimeExpires()
                            .before(Date.from(threshold.toInstant())))
                    .map(PreauthenticatedRequestSummary::getId)
                    .toList();

            // delete the expired pre-authenticated requests
            L.forEach(id -> {
                DeletePreauthenticatedRequestRequest request = DeletePreauthenticatedRequestRequest.builder()
                        .namespaceName("ax0judwwk3y8")
                        .bucketName("user-assets")
                        .parId(id)
                        .build();
                client.deletePreauthenticatedRequest(request);
            });
        } catch (Exception e) {
            throw new StorageException("Failed to delete expired images", e);
        }
    }

}