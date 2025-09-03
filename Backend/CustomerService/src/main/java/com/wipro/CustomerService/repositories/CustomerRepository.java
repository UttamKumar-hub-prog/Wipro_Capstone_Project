package com.wipro.CustomerService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.CustomerService.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
