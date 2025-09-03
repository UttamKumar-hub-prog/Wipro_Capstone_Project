package com.wipro.NotificationService.services;

public interface NotificationService {
    
    /**
     * Sends a payment notification email asynchronously.
     * Validates that the amount is greater than 0 before sending.
     *
     * @param paymentId      ID of the payment
     * @param receiverId     ID of the user receiving the notification
     * @param receiverEmail  Email of the user
     * @param message        Notification message
     */
    void sendEmail(Long paymentId, Long receiverId, String receiverEmail, String message, Long amount);
}
