package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Feedback;
import com.service.FeedbackService;

@RestController
@Component
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	@PostMapping("/createfeedback")
	private Feedback createFeedback(@RequestBody Feedback feedback) {
		return feedbackService.createFeedback(feedback);
	}
	
	@GetMapping("/all")
	private List<Feedback> loadAllFeedback(){
		return feedbackService.getAllFeedback();
	}
}
