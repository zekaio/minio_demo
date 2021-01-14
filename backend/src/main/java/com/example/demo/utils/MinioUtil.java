package com.example.demo.utils;

import com.example.demo.config.MinioConfig;
import com.example.demo.exception.BusinessException;
import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    private static boolean bucketExists(String bucketName) {
        return minioClient.bucketExists(bucketName);
    }

    /**
     * 创建 bucket
     *
     * @param bucketName string 桶名
     */
//    @SneakyThrows(Exception.class)
//    public static void makeBucket(String bucketName) {
//        if (!bucketExists(bucketName)) {
//            minioClient.makeBucket(bucketName);
//        }
//    }

    /**
     * 获取 put 方法的临时授权链接
     *
     * @param bucketName    string 桶名
     * @param subBucketName string 多级目录
     * @param fileType      string 文件mime type
     * @return map 授权链接和文件路径
     */
    @SneakyThrows(Exception.class)
    public static Map<String, String> presignedPutObject(String bucketName, String subBucketName, String fileType) {
        if (!bucketExists(bucketName)) {
            throw new BusinessException(404, "桶不存在");
        }

        // 通过mimeType获取文件扩展名
        String extensionName;
        try {
            MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
            MimeType mimeType = mimeTypes.forName(fileType);
            extensionName = mimeType.getExtension();
        } catch (Exception e) {
            extensionName = "";
        }

        Map<String, String> map = new HashMap<>();

        if (!subBucketName.equals("") && !subBucketName.endsWith("/")) {
            subBucketName = subBucketName + "/";
        }

        // 生成uuid作为文件名，拼接对象名：多级目录/uuid.ext
        String objectName = subBucketName + UUID.randomUUID().toString() + extensionName;
        // 文件路径：bucketName/多级目录/uuid.ext
        String filePath = bucketName + "/" + objectName;

        String presignedURL = minioClient.presignedPutObject(bucketName, objectName, 600);
        map.put("presignedURL", presignedURL);
        map.put("filePath", filePath);

        return map;
    }
}
