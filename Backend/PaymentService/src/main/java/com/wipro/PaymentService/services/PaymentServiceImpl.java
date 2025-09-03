package com.wipro.PaymentService.services;

import java.time.LocalDateTime;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wipro.PaymentService.dto.AccountDTO;
import com.wipro.PaymentService.dto.AuditLogDTO;
import com.wipro.PaymentService.dto.CustomerDTO;
import com.wipro.PaymentService.dto.PaymentEventDTO;
import com.wipro.PaymentService.entities.Payment;
import com.wipro.PaymentService.entities.PaymentStatus;
import com.wipro.PaymentService.exceptions.InsufficientBalanceException;
import com.wipro.PaymentService.exceptions.PaymentProcessingException;
import com.wipro.PaymentService.feign.AccountClient;
import com.wipro.PaymentService.feign.AuditLogClient;
import com.wipro.PaymentService.feign.CustomerClient;
import com.wipro.PaymentService.repositorys.PaymentRepository;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final CustomerClient customerClient;
	private final AccountClient accountClient;
	private final AuditLogClient auditLogClient;

	private final PaymentRepository paymentRepository;

	private final KafkaTemplate<String, PaymentEventDTO> kafkaTemplate;
	private final String PAYMENT_TOPIC = "payment-notifications";

	@Override
	@Transactional
	public Payment processPayment(Long senderId, Long receiverId, Long amount) {

		// Validate positive amount
		if (amount == null || amount <= 0) {
			throw new InsufficientBalanceException("Insufficient balance in sender's account");
		}

		// fetch the sender and receiver information
		CustomerDTO sender = customerClient.getCustomerById(senderId);

		AccountDTO senderAccount = accountClient.getAccountDetailsById(sender.getId());

		CustomerDTO receiver = customerClient.getCustomerById(receiverId);

		AccountDTO receiverAccount = accountClient.getAccountDetailsById(receiver.getId());

		// validating the balance if the verify the sender account have insufficient
		// balance

		// Create initial payment record
		Payment payment = new Payment();
		payment.setSenderName(sender.getName());
		payment.setSenderAccountNumber(senderAccount.getAccNo());
		payment.setReceiverName(receiver.getName());
		payment.setReceiverAccountNumber(receiverAccount.getAccNo());
		payment.setAmount(amount);
		payment.setPaymentStatus(PaymentStatus.PENDING);

		Payment savedPayment = paymentRepository.save(payment);

		try {
		    // Check balance
		    if (senderAccount.getBalance() < amount) {
		        throw new InsufficientBalanceException("Insufficient balance in sender's account");
		    }

		    // Debit sender and credit receiver
		    senderAccount.setBalance(senderAccount.getBalance() - amount);
		    receiverAccount.setBalance(receiverAccount.getBalance() + amount);

		    accountClient.updateAccount(senderAccount.getId(), senderAccount);
		    accountClient.updateAccount(receiverAccount.getId(), receiverAccount);

		    // Update status
		    savedPayment.setPaymentStatus(PaymentStatus.SUCCESS);

		    // Create event
		    PaymentEventDTO event = new PaymentEventDTO();
		    event.setPaymentId(savedPayment.getPaymentId());
		    event.setSenderId(senderId);
		    event.setReceiverId(receiver.getId());
		    event.setReceiverEmail(receiver.getEmail());
		    event.setAmount(amount);
		    event.setStatus("SUCCESS");
		    event.setTimestamp(LocalDateTime.now());

		    // Send to Kafka
		    try {
		        kafkaTemplate.send(PAYMENT_TOPIC, event);
		    } catch (Exception e) {
		        logAudit(senderId, savedPayment.getPaymentId(), amount, "SUCCESS_WITHOUT_NOTIFICATION",
		                 "Payment successful but Kafka failed: " + e.getMessage());
		    }

		    paymentRepository.save(savedPayment);
		    logAudit(senderId, savedPayment.getPaymentId(), amount, "SUCCESS", "Payment successful");

		    return savedPayment;

		} catch (FeignException fe) {
		    savedPayment.setPaymentStatus(PaymentStatus.FAILED);
		    paymentRepository.save(savedPayment);
		    logAudit(senderId, savedPayment.getPaymentId(), amount, "FAILED", "Remote service error: " + fe.getMessage());
		    throw new PaymentProcessingException("Payment failed due to remote service error");

		} catch (Exception e) {
		    savedPayment.setPaymentStatus(PaymentStatus.FAILED);
		    paymentRepository.save(savedPayment);
		    logAudit(senderId, savedPayment.getPaymentId(), amount, "FAILED", "Unexpected error: " + e.getMessage());
		    throw new PaymentProcessingException("Payment failed");
		}
	}
	// Helper method for audit logging
	private AuditLogDTO logAudit(Long userId, Long paymentId, Long amount, String status, String remarks) {
		AuditLogDTO auditLog = new AuditLogDTO();
		auditLog.setServiceName("PaymentService");
		auditLog.setAction("PAYMENT_PROCESSED");
		auditLog.setStatus(status);
		auditLog.setReferenceId(paymentId);
		auditLog.setUserId(userId);
		auditLog.setAmount(amount);
		auditLog.setRemarks(remarks);
		auditLog.setCreatedAt(LocalDateTime.now());

		try {
			auditLogClient.log(auditLog);
		} catch (Exception e) {
			// Optionally persist locally if AuditService is down
		}
		return auditLog;

	}

}
