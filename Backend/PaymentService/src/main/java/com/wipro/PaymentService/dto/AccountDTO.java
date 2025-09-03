package com.wipro.PaymentService.dto;

import com.wipro.PaymentService.enums.Enums.BankAccountType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String pan;
	private String aadhaar;
	private String accNo;
	private BankAccountType bankType;
	private Long balance;
	private Long loan;
	private String address;
}
