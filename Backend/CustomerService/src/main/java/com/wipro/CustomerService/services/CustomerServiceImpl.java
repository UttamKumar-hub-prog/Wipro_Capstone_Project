package com.wipro.CustomerService.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wipro.CustomerService.dto.FeedbackDTO;
import com.wipro.CustomerService.entities.Customer;
import com.wipro.CustomerService.exception.CustomerNotFoundException;
import com.wipro.CustomerService.feign.FeedbackClient;
import com.wipro.CustomerService.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;
	private final FeedbackClient feedbackClient;

	@Override
	public Customer saveCustomerDetails(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Page<Customer> getAllCustomers(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	@Override
	public Customer getCustomerByid(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer Not found:" + id));
	}

	@Override
	public FeedbackDTO saveCustomerFeedback(FeedbackDTO feedbackDTO) {
		return  feedbackClient.submitFeedback(feedbackDTO);
	}

}
