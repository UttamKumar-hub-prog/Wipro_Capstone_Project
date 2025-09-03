package com.wipro.AccountService.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wipro.AccountService.dto.CustomerDTO;
import com.wipro.AccountService.entities.Account;
import com.wipro.AccountService.exception.AccountNotFoundException;
import com.wipro.AccountService.feign.CustomerClient;
import com.wipro.AccountService.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	private final CustomerClient customerClient;

	@Override
	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}

	@Override
	public Account getAccountByAddress(String address) {
		return accountRepository.findByAddress(address)
				.orElseThrow(() -> new AccountNotFoundException("Account not found with address" + address));
	}

	@Override
	public Account getAccountById(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));

	}

	@Override
	public List<Account> getAllAccounts(Pageable pageable) {
		return accountRepository.findAll();
	}

	@Override
	public Account updateAccount(Long id, Account accountDetails) {
	    return accountRepository.findById(id)
	        .map(existingAccount -> {
	            existingAccount.setUsername(accountDetails.getUsername());
	            existingAccount.setPan(accountDetails.getPan());
	            existingAccount.setAadhaar(accountDetails.getAadhaar());
	            existingAccount.setAccNo(accountDetails.getAccNo());
	            existingAccount.setBankType(accountDetails.getBankType());
	            existingAccount.setBalance(accountDetails.getBalance());
	            existingAccount.setLoan(accountDetails.getLoan());
	            existingAccount.setAddress(accountDetails.getAddress());
	            return accountRepository.save(existingAccount);
	        })
	        .orElse(null);
	}


	@Override
	public CustomerDTO fetchCustomerDetails(Long id) {
		return customerClient.getCustomerById(id);
	}

}
