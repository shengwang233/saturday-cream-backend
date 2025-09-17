package com.saturday.controller.admin;

import com.saturday.properties.AwsS3Properties;
import com.saturday.result.Result;
import com.saturday.utils.AwsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/admin/common")
public class CommonController {
    @Autowired
    private AwsUtil awsUtil;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file)  {
        String key = "images/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String imageUrl = null;
        try {
            imageUrl = awsUtil.uploadFile(key, file);
            return Result.success(imageUrl);

        } catch (IOException e) {
            return Result.error("Upload Failed!");
        }
    }

}
