package com.mahendra.demo2;

import java.util.List;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class Main {

    static final String QUEUE_NAME = "queue1";
    static final String QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/027773602802/queue1";

    public static void main(String[] args) {
        AmazonSQS client = AmazonSQSClientBuilder.defaultClient();

        ReceiveMessageResult rest = client.receiveMessage(QUEUE_NAME);
        

        List<Message> messages = rest.getMessages();

        for(Message m : messages){
            System.out.println(m.getBody()+": "+m.getMessageId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            client.deleteMessage(QUEUE_URL,m.getReceiptHandle());
        }

        /*
        SendMessageRequest req = new SendMessageRequest().withQueueUrl(QUEUE_URL).withMessageBody("Hello Bengaluru")
                .withDelaySeconds(3);
        client.sendMessage(req);
        System.out.println("Message was sent to SQS");
        */
    }
}