package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.FeedBack;
import com.service.FeedbackService;

@RequestMapping("/ms/feedback")
@RestController
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;
	
	
	@GetMapping("/all")
	public List<FeedBack> getAllFeedbacks(){
		return feedbackService.getAllFeedbacks();
	}
	
	@PostMapping("/add")
	public String createFeedback(@RequestBody FeedBack feedBack) {
		return feedbackService.createFeedback(feedBack.getBody(), feedBack.getVendorName(), feedBack.getReviewer());
	}
}
