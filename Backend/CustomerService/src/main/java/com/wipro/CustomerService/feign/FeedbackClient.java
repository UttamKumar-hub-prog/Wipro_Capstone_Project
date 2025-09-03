package com.wipro.CustomerService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wipro.CustomerService.dto.FeedbackDTO;

@FeignClient(name = "FEEDBACK-SERVICE", path = "/feedbacks")
public interface FeedbackClient {

 
    @PostMapping("/")
    FeedbackDTO submitFeedback(@RequestBody FeedbackDTO feedbackDTO);
}