package com.udit.crudapp.minio;

import io.minio.MinioClient;
import io.minio.messages.Bucket;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.DownloadObjectArgs;
import io.minio.errors.MinioException;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;

@Service
public class MinioAdapter {

    @Autowired
    MinioClient minioClient;

    @Value("${minio.buckek.name}")
    String defaultBucketName;

    @Value("${minio.default.folder}")
    String defaultBaseFolder;

    public List<Bucket> getAllBuckets() {
        try {
            return minioClient.listBuckets();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public ObjectWriteResponse uploadObject(UploadObjectArgs args) {
        File file = new File("/tmp/" + name);
        file.canWrite();
        file.canRead();
        try {
            minioClient.uploadObject(
          UploadObjectArgs.builder()
              .bucket(defaultBucketName)
              .object(defaultBaseFolder+name)
              .filename(file.getAbsolutePath())
              .build());
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }

    }

    public void getFile(String key) {
        try {
            minioClient.downloadObject(
                DownloadObjectArgs.builder()
                .bucket(defaultBucketName)
                .object(defaultBaseFolder+"/"+key)
                .filename(key)
                .build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
    }
}