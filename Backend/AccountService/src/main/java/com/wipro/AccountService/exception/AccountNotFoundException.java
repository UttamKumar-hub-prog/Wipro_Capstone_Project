package com.wipro.AccountService.exception;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8281209293017305816L;

	public AccountNotFoundException(String message) {
		super(message);
	}

}
