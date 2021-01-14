package com.example.demo.controllers;

import com.example.demo.VO.Result;
import com.example.demo.services.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/minio")
public class MinioController {
    @Autowired
    private MinioService minioService;

//    @GetMapping("/bucket")
//    public Result bucketExists(@RequestParam("bucketName") String bucketName) {
//        return minioService.bucketExists(bucketName);
//    }
//
//    @PutMapping("/bucket")
//    public Result makeBucket(@RequestBody Map<String, String> params) {
//        return minioService.makeBucket(params.get("bucketName"));
//    }

    /**
     * 获取 put 方法的临时授权链接
     *
     * @param bucketName    string 桶名
     * @param subBucketName string 多级目录
     * @param fileType      string 文件mime type
     * @return 授权链接和文件路径
     */
    @GetMapping("/presigned/put")
    public Result presignedPutObject(
            @RequestParam("bucketName") String bucketName,
            @RequestParam(value = "subBucketName", defaultValue = "") String subBucketName,
            @RequestParam("fileType") String fileType) {
        return minioService.presignedPutObject(bucketName, subBucketName, fileType);
    }
}
