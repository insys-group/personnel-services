package com.insys.trapps.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by areyna on 5/25/17.
 */
@Configuration
public class AWSConfiguration {

    @Value("${aws.s3.endpoint}")
    private String ENDPOINT;

    @Value("${aws.s3.credentials.accessKey}")
    private String ACCESS_KEY;

    @Value("${aws.s3.credentials.secretKey}")
    private String SECRET_KEY;

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
    }

    @Bean
    public AmazonS3Client amazonS3Client(AWSCredentials awsCredentials) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
        amazonS3Client.setEndpoint(ENDPOINT);
        return amazonS3Client;
    }

}
