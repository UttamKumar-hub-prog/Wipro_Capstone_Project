package com.wipro.PaymentService.services;

import com.wipro.PaymentService.entities.Payment;

public interface PaymentService {

	Payment processPayment(Long senderId, Long receiverId, Long amount);

}
