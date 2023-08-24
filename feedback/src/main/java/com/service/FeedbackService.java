package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Feedback;
import com.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;
	
	public Feedback createFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}
	
	public List<Feedback> getAllFeedback(){
		return (List<Feedback>) feedbackRepository.findAll();
		}
}
