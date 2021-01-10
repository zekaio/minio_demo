package com.example.demo.services;


import com.example.demo.VO.Result;

public interface MinioService {
    Result bucketExists(String bucketName);

    Result makeBucket(String bucketName);

    Result presignedPutObject(String bucketName, String objectName);
}
