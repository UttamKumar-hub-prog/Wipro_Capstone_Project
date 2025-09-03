package com.wipro.PaymentService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
