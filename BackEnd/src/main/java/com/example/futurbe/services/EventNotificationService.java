package com.example.futurbe.services;

import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
@AllArgsConstructor
public class EventNotificationService {

    private final QRCodeService qrCodeService;
    private final EmailService emailService;

    public void sendEventEmailWithQRCode(String toEmail, String subject, String body, String qrCodeText) throws WriterException, IOException, MessagingException, jakarta.mail.MessagingException {
        byte[] qrCode = qrCodeService.generateQRCode(qrCodeText, 200, 200);
        emailService.sendEmailWithAttachment(toEmail, subject, body, qrCode, "QRCode.png");
    }
}
