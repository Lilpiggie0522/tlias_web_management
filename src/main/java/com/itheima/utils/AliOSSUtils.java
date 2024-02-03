package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
@Slf4j
@Component
public class AliOSSUtils {

    @Autowired
    private AliCloudProperties properties;

    /*@Value("${alicloud.oss.endpoint}")
    private String endpoint;
    @Value("${alicloud.oss.bucketName}")
    private String bucketName;*/

    private EnvironmentVariableCredentialsProvider credentialsProvider;

    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException, ClientException {
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();
        String endpoint = properties.getEndpoint();
        String bucketName = properties.getBucketName();
        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        log.info("filename: {} !!!!!!!!!!!!!", fileName);
        //上传文件到 OSS
        credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
//        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream);
        PutObjectResult result = ossClient.putObject(putObjectRequest);

        //文件访问路径
        String selfUrl = "https://".concat(bucketName.concat(".").concat(endpoint).concat("/").concat(fileName));
//        String selfUrl = "https://".concat(bucketName.concat(".").concat(endpoint).concat("/").concat(fileName));
        log.info("final url to return is {} !!!!!!!!!!!!!!!!!!!", selfUrl);
        // 关闭ossClient
        ossClient.shutdown();
        return selfUrl;// 把上传到oss的路径返回
    }

}