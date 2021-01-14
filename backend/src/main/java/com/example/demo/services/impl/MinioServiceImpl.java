package com.example.demo.services.impl;

import com.example.demo.VO.Result;
import com.example.demo.services.MinioService;
import com.example.demo.utils.MinioUtil;
import org.springframework.stereotype.Service;

@Service("MinioService")
public class MinioServiceImpl implements MinioService {

//    @Override
//    public Result bucketExists(String bucketName) {
//        return Result.success(MinioUtil.bucketExists(bucketName));
//    }
//
//    @Override
//    public Result makeBucket(String bucketName) {
//        MinioUtil.makeBucket(bucketName);
//        return Result.success();
//    }

    @Override
    public Result presignedPutObject(String bucketName, String subBucketName, String fileType) {
        return Result.success(MinioUtil.presignedPutObject(bucketName, subBucketName, fileType));
    }
}
