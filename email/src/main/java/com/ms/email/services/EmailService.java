package com.ms.email.services;

import com.ms.email.enums.StatusEmail;
import com.ms.email.models.Email;
import com.ms.email.repositories.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender mailSender;

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public Email sendEmail(Email email) {
        try {
            email.setSendDateTime(LocalDateTime.now());
            email.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());

            mailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);
        }
        catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        }

        return emailRepository.save(email);
    }

}
