package com.wipro.CustomerService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is Required")
	private String name;

	@Email(message = "Invalid Email Format")
	@NotBlank(message = "Email is Required")
	private String email;

	@NotBlank(message = "Phone is Required")
	private String phone;

	@NotBlank(message = "Address is Required")
	private String address;

	@Pattern(regexp = "^[0-9]{12}$", message = "Aadhaar Must be 12 digits")
	private String aadhaar;

	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN Format")
	private String pan;

	private Integer age;
	
	private String accountNumber;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private KycStatus kycStatus;

	@Enumerated(EnumType.STRING)
	private AccountType accountType;

}
