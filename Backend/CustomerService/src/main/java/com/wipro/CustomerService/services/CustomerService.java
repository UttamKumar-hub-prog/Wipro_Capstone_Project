package com.wipro.CustomerService.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wipro.CustomerService.dto.FeedbackDTO;
import com.wipro.CustomerService.entities.Customer;

public interface CustomerService {

	Customer saveCustomerDetails(Customer customer);

	Page<Customer> getAllCustomers(Pageable pageable);

	Customer getCustomerByid(Long id);

	FeedbackDTO saveCustomerFeedback(FeedbackDTO feedbackDTO);

}
