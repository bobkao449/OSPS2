package net.enjoy.springboot.registrationlogin.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Async
    public void sendWelcomeEmail(String userEmail) {
        // 模拟发送邮件
        try {
            // 模拟邮件发送延迟
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sending welcome email to " + userEmail);
    }
}
