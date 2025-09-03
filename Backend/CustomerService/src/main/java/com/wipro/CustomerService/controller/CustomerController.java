package com.wipro.CustomerService.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.CustomerService.dto.FeedbackDTO;
import com.wipro.CustomerService.entities.Customer;
import com.wipro.CustomerService.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping("/saveDetails")

	public ResponseEntity<Customer> SaveCustomerDetails(@RequestBody Customer customer) {
		return ResponseEntity.ok(customerService.saveCustomerDetails(customer));
	}

	@GetMapping("/getAll")

	public ResponseEntity<List<Customer>> GetAllCustomers(Pageable pageable) {

		return ResponseEntity.ok(customerService.getAllCustomers(pageable).getContent());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> GetCustomerById(@PathVariable Long id) {
		return ResponseEntity.ok(customerService.getCustomerByid(id));
	}
	
	@PostMapping("/feedback")
	public ResponseEntity<FeedbackDTO> saveFeedback(@RequestBody FeedbackDTO feedbackDTO) {
	    FeedbackDTO saved = customerService.saveCustomerFeedback(feedbackDTO);
	    return ResponseEntity.ok(saved);
	}

}
