package com.springboot.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendVerificationEmail(String email, String token) {
    String subject = "Account Verification";
    String verificationUrl = "http://localhost:3000/email-confirmation?token=" + token;
    String message = "Please click the following link to verify your account:\n" + verificationUrl;

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(email);
    mailMessage.setSubject(subject);
    mailMessage.setText(message);
    mailMessage.setFrom("huynhgiaphu2710@gmail.com");

    mailSender.send(mailMessage);
  }
}
