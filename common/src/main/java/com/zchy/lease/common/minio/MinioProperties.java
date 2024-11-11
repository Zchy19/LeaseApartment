package com.zchy.lease.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @projectName: lease
 * @package: com.zchy.lease.common.minio
 * @className: MinioProperties
 * @author: ZCH
 * @description:
 * @date: 8/4/2024 7:53 PM
 * @version: 1.0
 */
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
