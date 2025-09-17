package com.saturday.controller.admin;

import com.saturday.properties.AwsS3Properties;
import com.saturday.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@RestController
@RequestMapping("/admin/common")
public class CommonController {
    @Autowired
    private AwsS3Properties awsS3Properties;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){


        return null;

    }

}
