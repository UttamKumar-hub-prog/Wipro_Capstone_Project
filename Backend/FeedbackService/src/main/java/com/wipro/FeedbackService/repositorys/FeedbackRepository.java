package com.wipro.FeedbackService.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.FeedbackService.entities.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
