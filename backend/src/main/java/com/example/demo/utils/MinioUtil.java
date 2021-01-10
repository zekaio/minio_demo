package com.example.demo.utils;

import com.example.demo.config.MinioConfig;
import com.example.demo.exception.BusinessException;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MinioUtil {

    @Autowired
    private MinioConfig minioConfig;

    private static MinioClient minioClient;

    /**
     * 初始化minio客户端
     */
    @PostConstruct
    public void init() {
        try {
            minioClient = new MinioClient(minioConfig.getUrl(), minioConfig.getAccessKey(), minioConfig.getSecretKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 判断 bucket 是否存在
     *
     * @param bucketName string 桶名
     * @return boolean 是否存在
     */
    @SneakyThrows(Exception.class)
    public static boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(bucketName);
    }

    /**
     * 创建 bucket
     *
     * @param bucketName string 桶名
     */
    @SneakyThrows(Exception.class)
    public static void makeBucket(String bucketName) {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(bucketName);
        }
    }

    /**
     * 获取 put 方法的临时授权链接
     *
     * @param bucketName string 桶名
     * @param objectName string 要保存的对象的名字
     * @return string 地址
     */
    @SneakyThrows(Exception.class)
    public static String presignedPutObject(String bucketName, String objectName) {
        if (!bucketExists(bucketName)) {
            throw new BusinessException(404, "桶不存在");
        }
        return minioClient.presignedPutObject(bucketName, objectName);
    }
}
