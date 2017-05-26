package com.insys.trapps.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by areyna on 5/25/17.
 */
public interface S3Service {

    Boolean createBucket(String bucketName);

    List<S3ObjectSummary> list(String bucket);

//    List<PutObjectResult> upload(String bucket, MultipartFile[] multipartFiles);

    PutObjectResult upload(String bucket, InputStream inputStream, String uploadKey);

    byte[] download(String bucket, String key);

    Boolean remove(String bucket, String key);

}
