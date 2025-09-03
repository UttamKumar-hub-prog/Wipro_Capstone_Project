package com.wipro.NotificationService.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PaymentEventDTO {
	private Long paymentId;
    private Long senderId;
    private String senderEmail;
    private Long receiverId;
    private String receiverEmail;
    private Long amount;
    private String status;
    private LocalDateTime timestamp;
}
