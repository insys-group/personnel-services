package com.insys.trapps.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.insys.trapps.service.S3Service;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by areyna on 5/25/17.
 */
@Service
@Transactional
public class S3ServiceImpl implements S3Service {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);

    @Autowired
    private AmazonS3Client amazonS3Client;

    public PutObjectResult upload(String bucket, InputStream inputStream, String uploadKey) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey, inputStream, new ObjectMetadata());

        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

        PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);

        IOUtils.closeQuietly(inputStream);

        return putObjectResult;
    }

    public byte[] download(String bucket, String key) {
        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);

            S3Object s3Object = amazonS3Client.getObject(getObjectRequest);

            S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

            return IOUtils.toByteArray(objectInputStream);
        } catch (Exception ex) {
            logger.error("Error downloading the file in s3", ex);
            return null;
        }
    }

    public List<S3ObjectSummary> list(String bucket) {
        ObjectListing objectListing = amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(bucket));

        List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();

        return s3ObjectSummaries;
    }

    public Boolean createBucket(String bucket) {
        try {
            return amazonS3Client.createBucket(bucket) != null;
        } catch (Exception ex) {
            logger.error("Error creating bucket in s3.", ex);
            return false;
        }
    }

    public Boolean remove(String bucket, String key) {
        try {
            amazonS3Client.deleteObject(bucket, key);
            return true;
        } catch (Exception ex) {
            logger.error("Error creating bucket in s3.", ex);
            return false;
        }
    }

}