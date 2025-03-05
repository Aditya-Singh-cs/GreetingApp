package com.example.GreetingApp.controller;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.GreetingApp.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // ✅ API to send a simple email
    @GetMapping("/send")
    public String sendTestEmail(@RequestParam String to) {
        emailService.sendSimpleEmail(to, "Test Email", "Hello! This is a test email from Spring Boot.");
        return "Simple email sent successfully!";
    }

    // ✅ API to send an email with an attachment
    @GetMapping("/sendWithAttachment")
    public String sendEmailWithAttachment(@RequestParam String to, @RequestParam String filePath) {
        try {
            emailService.sendEmailWithAttachment(to, "Test Email with Attachment", "Please find the attachment below.", filePath);
            return "Email with attachment sent successfully!";
        } catch (MessagingException e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}