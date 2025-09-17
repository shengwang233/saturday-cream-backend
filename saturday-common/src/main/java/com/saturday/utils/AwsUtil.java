package com.saturday.utils;

import com.saturday.properties.AwsS3Properties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
@Component
@RequiredArgsConstructor

public class AwsUtil {
    private final S3Client s3Client;
    private final AwsS3Properties awsProps;

    public String uploadFile(String key, MultipartFile file) throws IOException {
        PutObjectRequest putRequest = PutObjectRequest.builder()
                .bucket(awsProps.getBucket())
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putRequest, RequestBody.fromBytes(file.getBytes()));


        return "https://" + awsProps.getBucket() + ".s3." + awsProps.getRegion()
                + ".amazonaws.com/" + key;
    }

    public void deleteFile(String key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(awsProps.getBucket())
                .key(key)
                .build();
        s3Client.deleteObject(deleteRequest);
    }
}
