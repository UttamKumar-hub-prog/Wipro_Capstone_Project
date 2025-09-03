package com.wipro.AccountService.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.wipro.AccountService.dto.CustomerDTO;
import com.wipro.AccountService.entities.Account;

public interface AccountService {

	Account saveAccount(Account account);

	Account getAccountByAddress(String address);

	Account getAccountById(Long id);
	List<Account> getAllAccounts(Pageable pageable);

	CustomerDTO fetchCustomerDetails(Long id);

	Account updateAccount(Long id, Account accountDetails);



}
