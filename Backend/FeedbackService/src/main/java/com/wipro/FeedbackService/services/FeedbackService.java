package com.wipro.FeedbackService.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.FeedbackService.dto.FeedbackDTO;
import com.wipro.FeedbackService.entities.Feedback;
import com.wipro.FeedbackService.repositorys.FeedbackRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setName(feedbackDTO.getName());
        feedback.setEmail(feedbackDTO.getEmail());
        feedback.setMessage(feedbackDTO.getMessage());
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
}