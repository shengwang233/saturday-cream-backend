package com.saturday.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "saturday.aws")
@Data
public class AwsS3Properties {

    private String bucket;
    private String region;
    private String accessKey;
    private String secretKey;

}
