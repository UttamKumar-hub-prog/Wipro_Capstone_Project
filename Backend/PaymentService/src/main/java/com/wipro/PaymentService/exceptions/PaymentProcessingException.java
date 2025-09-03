package com.wipro.PaymentService.exceptions;

public class PaymentProcessingException extends RuntimeException {
	public PaymentProcessingException (String message) {
		super(message);
	}

}
