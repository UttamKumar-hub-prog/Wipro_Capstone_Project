package com.wipro.NotificationService.services;

import java.time.LocalDateTime;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.NotificationService.entities.Notification;
import com.wipro.NotificationService.repositorys.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    /**
     * Save notification to DB
     */
    @Transactional
    public Notification saveNotification(Long paymentId, Long userId, String message, Long amount) {
        Notification notification = new Notification();
        notification.setPaymentId(paymentId);
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setStatus("PENDING");
        notification.setCreatedAt(LocalDateTime.now());

        return notificationRepository.save(notification);
    }

    /**
     * Send email asynchronously, validate amount before sending
     */
    @Async
    public void sendEmail(Long paymentId, Long userId, String recipientEmail, String message, Long amount) {
        // Validate amount
        if (amount <= 0) {
            System.err.println("Invalid payment amount (" + amount + "). Notification not sent.");
            return;
        }

        // Save notification first
        Notification notification = saveNotification(paymentId, userId, message, userId);

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(recipientEmail);
            mailMessage.setSubject("Payment Notification");
            mailMessage.setText(message);
           

            mailSender.send(mailMessage);

            // Update notification status
            notification.setStatus("SENT");
            notificationRepository.save(notification);

            System.out.println("Email sent to user " + userId + ": "+amount + message);

        } catch (Exception e) {
            // Update status as FAILED
            notification.setStatus("FAILED");
            notificationRepository.save(notification);

            System.err.println("Failed to send email to user " + userId + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
 
}
