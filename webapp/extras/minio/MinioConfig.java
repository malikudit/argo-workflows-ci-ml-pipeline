package com.udit.crudapp.minio;

import io.minio.MinioClient;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Value("${minio.access.name}")
    String accessKey;
    @Value("${minio.access.secret}")
    String accessSecret;
    @Value("${minio.url}")
    String minioUrl;

    @Bean
    public MinioClient generateMinioClient() {
        try {
            MinioClient client = MinioClient.builder()
            .endpoint("https://127.0.0.1:9000")
            .credentials("minioadmin", "minioadmin")
            .build();
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


}