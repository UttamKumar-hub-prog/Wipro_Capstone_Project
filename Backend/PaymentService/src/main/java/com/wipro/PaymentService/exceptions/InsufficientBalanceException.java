package com.wipro.PaymentService.exceptions;

public class InsufficientBalanceException  extends RuntimeException {
	
	public InsufficientBalanceException (String message) {
		super(message);
	}

}
