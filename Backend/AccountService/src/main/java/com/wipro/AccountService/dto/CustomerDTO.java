package com.wipro.AccountService.dto;

import com.wipro.AccountService.enums.Enums.AccountType;
import com.wipro.AccountService.enums.Enums.Gender;
import com.wipro.AccountService.enums.Enums.KycStatus;

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
