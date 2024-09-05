package org.ichat.backend.services.shared.implementation;

import com.oracle.bmc.objectstorage.model.CreatePreauthenticatedRequestDetails;
import com.oracle.bmc.objectstorage.model.ObjectSummary;
import com.oracle.bmc.objectstorage.model.PreauthenticatedRequest;
import com.oracle.bmc.objectstorage.model.PreauthenticatedRequestSummary;
import com.oracle.bmc.objectstorage.requests.*;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.responses.ListObjectsResponse;
import com.oracle.bmc.objectstorage.responses.ListPreauthenticatedRequestsResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ichat.backend.exception.StorageException;
import org.ichat.backend.model.util.storage.StorageResponseDTO;
import org.ichat.backend.services.account.IUserService;
import org.ichat.backend.services.shared.IStorageService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StorageService implements IStorageService {
    private final ObjectStorageClient client;

    @Override
    public StorageResponseDTO createPreAuthenticatedRequest(String bucketName) throws StorageException {
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4); // 2 hours

        try {
            CreatePreauthenticatedRequestDetails details = CreatePreauthenticatedRequestDetails.builder()
                    .name("PAR-" + expiration.getTime())
                    .accessType(CreatePreauthenticatedRequestDetails.AccessType.AnyObjectReadWrite)
                    .bucketListingAction(PreauthenticatedRequest.BucketListingAction.Deny)
                    .timeExpires(expiration)
                    .build();

            CreatePreauthenticatedRequestRequest request = CreatePreauthenticatedRequestRequest.builder()
                    .namespaceName("ax0judwwk3y8")
                    .bucketName(bucketName)
                    .createPreauthenticatedRequestDetails(details)
                    .build();

            PreauthenticatedRequest PAR  = client.createPreauthenticatedRequest(request).getPreauthenticatedRequest();
            LocalDateTime expires = LocalDateTime.ofInstant(PAR.getTimeExpires().toInstant(), ZoneId.systemDefault());
            return new StorageResponseDTO(PAR.getAccessUri(), expires);
        } catch (Exception e) {
            throw new StorageException("Failed to create pre-authenticated request", e);
        }
    }

    @Override
    public void uploadBase64Image(String base64, String objectNamePath) throws StorageException {
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            InputStream IS = new ByteArrayInputStream(bytes);
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .namespaceName("ax0judwwk3y8")
                    .bucketName("user-assets")
                    .objectName(objectNamePath)
                    .putObjectBody(IS)
                    .contentLength((long) bytes.length)
                    .build();
            client.putObject(objectRequest);
            IS.close();
        } catch (Exception e) {
            throw new StorageException("Failed to store image", e);
        }
    }

    @Override
    public void uploadImageFromUrl(String url, String bucketName, String objectNamePath) throws StorageException {
        try (InputStream IS = new URL(url).openStream()) {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .namespaceName("ax0judwwk3y8")
                    .bucketName(bucketName)
                    .objectName(objectNamePath)
                    .contentType(MediaType.IMAGE_PNG_VALUE)
                    .putObjectBody(IS)
                    .build();
            client.putObject(objectRequest);
        } catch (Exception e) {
            throw new StorageException("Failed to store image", e);
        }
    }

    @Override
    public void deleteUnusedImages(IUserService userService) throws StorageException {
        try {
            ListObjectsRequest list_req = ListObjectsRequest.builder()
                    .namespaceName("ax0judwwk3y8")
                    .bucketName("user-assets")
                    .startAfter("profile/images/")
                    .limit(100)
                    .build();

            ListObjectsResponse response = client.listObjects(list_req);
            var objects = response.getListObjects().getObjects();
            List<String> unusedObjectsNames = objects.stream()
                    .map(ObjectSummary::getName)
                    .filter(name -> !userService.existsByImage(name))
                    .toList();

            unusedObjectsNames.forEach(objectName ->{
                DeleteObjectRequest delete_rep = DeleteObjectRequest.builder()
                        .namespaceName("ax0judwwk3y8")
                        .bucketName("user-assets")
                        .objectName(objectName)
                        .build();
                client.deleteObject(delete_rep);
            });
        } catch (Exception e) {
            throw new StorageException("Failed to delete expired user images", e);
        }
    }


    @Override
    public void deleteExpiredPARs(OffsetDateTime threshold) throws StorageException {
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
            throw new StorageException("Failed to delete expired PARs", e);
        }
    }

}