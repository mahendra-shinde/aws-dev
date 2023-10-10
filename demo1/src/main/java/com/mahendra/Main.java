package com.mahendra;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class Main {
    public static void main(String[] args) throws IOException {
        AmazonS3 client = initBucket();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the message: ");
        String line = sc.nextLine();
        sc.close();
        File file = File.createTempFile("temp", "text");
        FileWriter writer = new FileWriter(file, Charset.defaultCharset(), false);
        writer.append(line);
        writer.flush();
        writer.close();
        System.out.println("Temp file: " + file.getAbsolutePath());
        client.putObject("bucket00898", "messages.txt", file);
        System.out.println("File uploaded.");
    }

    public static AmazonS3 initBucket() {
        // Use Access Key ID and Access Key Secret

        AmazonS3 client = AmazonS3ClientBuilder.standard().withCredentials(
                /// Search for .aws/credentials file in USER HOME directory
                // c:\\users\\mahendra\\.aws\\credentials
                new ProfileCredentialsProvider()).withRegion(Regions.US_EAST_1).build();

        String bucket = "bucket00898";
        if (!client.doesBucketExistV2(bucket)) {
            client.createBucket(bucket);
        }

        return client;
    }

}