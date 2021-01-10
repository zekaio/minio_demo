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

    @GetMapping("/bucket")
    public Result bucketExists(@RequestParam("bucketName") String bucketName) {
        return minioService.bucketExists(bucketName);
    }

    @PutMapping("/bucket")
    public Result makeBucket(@RequestBody Map<String, String> params) {
        return minioService.makeBucket(params.get("bucketName"));
    }

    @GetMapping("/presigned/put")
    public Result presignedPutObject(@RequestParam("bucketName") String bucketName, @RequestParam("objectName") String objectName) {
        return minioService.presignedPutObject(bucketName, objectName);
    }
}
