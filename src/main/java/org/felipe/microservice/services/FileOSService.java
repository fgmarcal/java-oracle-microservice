package org.felipe.microservice.services;

import com.oracle.bmc.objectstorage.requests.GetObjectRequest;
import com.oracle.bmc.objectstorage.responses.GetObjectResponse;
import org.felipe.microservice.config.OSClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class FileOSService {

    @Value("${bucket.name}")
    String bucketName;
    @Value("${bucket.namespace}")
    String namespace;

    @Autowired
    private OSClientConfiguration clientConfiguration;

    public String getReportFileContent(String fileName){
        GetObjectRequest objectRequest = GetObjectRequest.builder()
                .namespaceName(namespace)
                .bucketName(bucketName)
                .objectName(fileName)
                .build();
        try {
            GetObjectResponse objectResponse = clientConfiguration.getObjectStorage().getObject(objectRequest);
            InputStream inputStream = objectResponse.getInputStream();

            String content = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());
            return content;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
