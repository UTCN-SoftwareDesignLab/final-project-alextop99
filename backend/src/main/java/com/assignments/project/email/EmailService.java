package com.assignments.project.email;

import lombok.AllArgsConstructor;
import org.springframework.core.io.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void emailCarFinished(String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("PTI Service - Car Finished");
        msg.setText("The car has been finished. Please come to pick it up!\nThank you for choosing us!");

        javaMailSender.send(msg);
    }

    public void emailReceipt(String email, String filename) throws MessagingException, FileNotFoundException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email);

        helper.setSubject("Receipt from PTI Service");
        helper.setText("Thank you for choosing us! \nThe receipt has been attached.");

        FileSystemResource resource = new FileSystemResource(new File("./"+filename));

        helper.addAttachment(filename, resource);
        javaMailSender.send(msg);
    }
}
