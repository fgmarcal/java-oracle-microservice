package org.felipe.microservice.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class EmailService {
    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    public void sendReport(String content, String to) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            int currentMonth = LocalDate.now().getMonthValue();
            int currentYear = LocalDate.now().getYear();
            helper.setFrom(from);
            helper.setText(content, true);
            helper.setSubject("Report do mês " + currentMonth + "/" + currentYear);
            helper.setTo(to);

            mailSender.send(message);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
