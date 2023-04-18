package com.udit.crudapp.controller;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {
  public static void main(String args[]) {
    uploaderFunction();
  }
  public static void uploaderFunction()
      throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      // Create a minioClient with the MinIO server playground, its access key and secret key.
      MinioClient minioClient =
          MinioClient.builder()
              .endpoint("http://127.0.0.1:9000")
              .credentials("minioadmin", "minioadmin")
              .build();

      boolean found =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket("workflows").build());
      if (!found) {
        // Make a new bucket called 'workflows'.
        minioClient.makeBucket(MakeBucketArgs.builder().bucket("workflows").build());
      } else {
        System.out.println("Bucket 'workflows' already exists.");
      }
/*
      String f = "http://localhost:8080/api/download/"+ fileName;
      minioClient.uploadObject(
          UploadObjectArgs.builder()
              .bucket("workflows")
              .object(fileName)
              .filename(f)
              .build());
      System.out.println(
          "Workflow successfully uploaded.");
          */
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
      System.out.println("HTTP trace: " + e.httpTrace());
    }
  }
}
