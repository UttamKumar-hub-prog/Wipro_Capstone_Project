package com.wipro.PaymentService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.PaymentService.dto.PaymentRequest;
import com.wipro.PaymentService.dto.PaymentResponseDTO;
import com.wipro.PaymentService.entities.Payment;
import com.wipro.PaymentService.services.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping("/transfer")
	public ResponseEntity<PaymentResponseDTO> processPayment(@Valid @RequestBody PaymentRequest request) {
	    Payment payment = paymentService.processPayment(
	            request.getSenderId(),
	            request.getReceiverId(),
	            request.getAmount()
	    );

	    PaymentResponseDTO response = new PaymentResponseDTO(
	            payment.getPaymentId(),
	            payment.getSenderName(),
	            payment.getReceiverName(),
	            payment.getAmount(),
	            payment.getPaymentStatus().name()
	    );

	    return ResponseEntity.ok(response);
	}


}
