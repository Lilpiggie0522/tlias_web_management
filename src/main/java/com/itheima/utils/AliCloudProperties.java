package com.itheima.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: AliCloudProperties
 * Package: com.itheima.utils
 * Description:
 *
 * @Author Piggie
 * @Create 26/01/2024 10:16 pm
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "alicloud.oss")
public class AliCloudProperties {
    private String endpoint;
    private String bucketName;
}
