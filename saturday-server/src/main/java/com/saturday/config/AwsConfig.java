package com.saturday.config;

import com.saturday.properties.AwsS3Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration

public class AwsConfig {
    @Bean
    public S3Client s3Client(AwsS3Properties awsProps) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                awsProps.getAccessKey(), awsProps.getSecretKey()
        );
        return S3Client.builder()
                .region(Region.of(awsProps.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}
