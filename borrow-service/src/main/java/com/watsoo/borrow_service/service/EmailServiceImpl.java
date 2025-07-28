package com.watsoo.borrow_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String email, String name, String message) {
        Runnable emailTask = () -> {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

                String subject = "📬 Borrowing Notification";

                String content = String.format(
                    "Dear %s,<br><br>" +
                    "%s<br><br>" +
                    "If you have any questions, feel free to reach out.<br><br>" ,
                    name, message
                );

                helper.setTo(email);
                helper.setSubject(subject);
                helper.setText(content, true);

                javaMailSender.send(mimeMessage);

                System.out.println("Email sent successfully to: " + email);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Failed to send email to: " + email);
            }
        };

        Thread emailThread = new Thread(emailTask);
        emailThread.setPriority(Thread.NORM_PRIORITY);
        emailThread.start();
    }
}
