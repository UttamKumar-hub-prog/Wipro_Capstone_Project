package com.wipro.PaymentService.dto;

import com.wipro.PaymentService.enums.Enums.AccountType;
import com.wipro.PaymentService.enums.Enums.Gender;
import com.wipro.PaymentService.enums.Enums.KycStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	private Long id;

	private String name;

	private String email;

	private String phone;

	private String address;

	private String aadhaar;

	private String pan;

	private Integer age;
	
	private String accountNumber;

	private Gender gender;

	private KycStatus kycStatus;

	private AccountType accountType;

}
