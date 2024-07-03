package com.team4chamchi.tunastudy.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
public class NotificationService {

    private final SnsClient snsClient;

    @Autowired
    public NotificationService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void sendMessage(String phone, String content) {
        try {
            // SMS 구성
            PublishRequest request = PublishRequest.builder()
                    .message(content)
                    .phoneNumber(phone)
                    .build();

            // SMS 발송
            snsClient.publish(request);
            System.out.println(phone + "문자 발송");

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}
