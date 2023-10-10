package com.mahendra.demo3;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;

public class Main {
    public static void main(String[] args) {
        
        AmazonSNS client = AmazonSNSClient.builder().withRegion(Regions.US_EAST_1)
				    .withCredentials(new ProfileCredentialsProvider()).build();
        PublishRequest req = new PublishRequest();
        String message = "World News";
        if(args.length >= 1 ){
            message = args[0];
        }
        req.setMessage(message);
        req.setTopicArn("arn:aws:sns:us-east-1:027773602802:topic1");
        client.publish(req);
        System.out.println("Message published !");
    }
}