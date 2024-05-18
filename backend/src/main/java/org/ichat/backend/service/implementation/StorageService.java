package org.ichat.backend.service.implementation;

import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.ObjectStorageClient;
import com.oracle.bmc.objectstorage.requests.PutObjectRequest;
import jakarta.transaction.Transactional;
import org.ichat.backend.exeception.StorageException;
import org.ichat.backend.model.util.StorageFile;
import org.ichat.backend.service.IStorageService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Transactional
public class StorageService implements IStorageService {
    ObjectStorageClient client;

    @Override
    public void init() throws IOException {
        final ConfigFileReader.ConfigFile configFile = ConfigFileReader.parseDefault();
        final AuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configFile);
        client = ObjectStorageClient.builder().build(provider);
    }


    @Override
    public StorageFile store(MultipartFile file) {
        StorageFile image;
        try {
            InputStream IS = file.getInputStream();
            image = new StorageFile(file.getOriginalFilename(), file.getBytes());
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .namespaceName("ax0judwwk3y8")
                    .bucketName("images")
                    .objectName(image.getName())
                    .contentType(image.getMediaType().toString())
                    .putObjectBody(IS)
                    .build();
            client.putObject(objectRequest);
            IS.close();
            return image;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        } finally {
            client.close();
        }
    }


    @Cacheable("images")
    @Override
    public StorageFile getImage(String filename) {
        StorageFile image;
        try {
            GetObjectRequest objectResponse = GetObjectRequest.builder()
                    .namespaceName("ax0judwwk3y8")
                    .bucketName("images")
                    .objectName(filename.toLowerCase())
                    .build();

            InputStream IS = client.getObject(objectResponse).getInputStream();
            image = new StorageFile(filename, IS.readAllBytes());
            IS.close();
        } catch (IOException e) {
            throw new StorageException("Failed to get file " + filename, e);
        } finally {
            client.close();
        }

        return image;
    }

}
