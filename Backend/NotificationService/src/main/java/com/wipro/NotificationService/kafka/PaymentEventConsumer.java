package com.wipro.NotificationService.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.wipro.NotificationService.dto.PaymentEventDTO;
import com.wipro.NotificationService.services.NotificationService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentEventConsumer {

	private final NotificationService notificationService;

	@KafkaListener(topics = "payment-notifications", groupId = "notification-service-group", containerFactory = "kafkaListenerContainerFactory")
	public void consume(PaymentEventDTO event) {

		String message = "Payment of amount " + event.getAmount() + " from user " + event.getSenderId() + " to user "
				+ event.getReceiverId() + " is " + event.getStatus();

		// Send email notification asynchronously
		notificationService.sendEmail(event.getPaymentId(), event.getSenderId(), event.getSenderEmail(), message, event.getAmount());
		notificationService.sendEmail(event.getPaymentId(), event.getReceiverId(), event.getReceiverEmail(), message, event.getAmount());
	}
}