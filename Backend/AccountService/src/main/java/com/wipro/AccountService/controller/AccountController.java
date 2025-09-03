package com.wipro.AccountService.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.AccountService.dto.CustomerDTO;
import com.wipro.AccountService.entities.Account;
import com.wipro.AccountService.services.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService accountService;

	@PostMapping("/")
	public ResponseEntity<Account> SaveAccount(@RequestBody Account account) {
		return ResponseEntity.ok(accountService.saveAccount(account));
	}
	 
	@GetMapping("/address/{address}")
	public ResponseEntity<Account> GetAccountByAddress(@PathVariable String address) {
		return ResponseEntity.ok(accountService.getAccountByAddress(address));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Account> GetAccountById(@PathVariable Long id) {
		return ResponseEntity.ok(accountService.getAccountById(id));
	}
	 
	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<Account>> GetAllAccounts(Pageable pageable) {
		return ResponseEntity.ok(accountService.getAllAccounts(pageable));
	}

	 
	@PutMapping("/{id}")
	public ResponseEntity<Account> updateAccount(
	        @PathVariable Long id, 
	        @RequestBody Account accountDetails) {

	    Account updatedAccount = accountService.updateAccount(id, accountDetails);
	    if (updatedAccount != null) {
	        return ResponseEntity.ok(updatedAccount);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}


	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerDTO> getCustomerInfo(@PathVariable Long id) {
		CustomerDTO customer = accountService.fetchCustomerDetails(id);
		return ResponseEntity.ok(customer);
	}

}
