package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: UpdateController
 * Package: com.itheima.controller
 * Description:
 *
 * @Author Piggie
 * @Create 24/01/2024 4:23 pm
 * @Version 1.0
 */
@RestController
@Slf4j
public class UpdateController {
    @Autowired private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException, ClientException {
        log.info("original file name is {} !!!!!!!!!!!!!!!!!!!!", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }

}
