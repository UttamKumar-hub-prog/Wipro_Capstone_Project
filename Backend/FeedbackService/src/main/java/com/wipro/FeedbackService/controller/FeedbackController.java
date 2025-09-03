package com.wipro.FeedbackService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.FeedbackService.dto.FeedbackDTO;
import com.wipro.FeedbackService.entities.Feedback;
import com.wipro.FeedbackService.services.FeedbackService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/saveFeedback")
    public ResponseEntity<Feedback> submitFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        Feedback savedFeedback = feedbackService.saveFeedback(feedbackDTO);
        return ResponseEntity.ok(savedFeedback);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }
}