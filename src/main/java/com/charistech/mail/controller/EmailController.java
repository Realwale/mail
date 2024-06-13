package com.charistech.mail.controller;

import com.charistech.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mail")
public class EmailController {

    private final MailService mailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String recipientEmail, @RequestParam String subject, @RequestParam String text) {
        try {
            mailService.sendEmail(recipientEmail, subject, text);
            return "Email sent successfully!";
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }
}
