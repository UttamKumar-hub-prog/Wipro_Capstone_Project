package com.wipro.AccountService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.AccountService.entities.Account;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long>{

	Optional<Account> findByAddress(String address);

}
