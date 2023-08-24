package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.model.FeedBack;
import com.model.Vendor;
import com.repo.FeedbackRepo;

@Service
public class FeedbackService {
	
	@Autowired
	FeedbackRepo feedbackRepo;
	
	@Autowired
	private MongoTemplate template;
	
	public List<FeedBack> getAllFeedbacks(){
		return feedbackRepo.findAll();
	}
	
	public String createFeedback(String body, String vendorName, String reviewer ) {
		FeedBack feedback = feedbackRepo.insert(new FeedBack(body, reviewer));
		template.update(Vendor.class).matching(Criteria.where("userName").is(vendorName))
				.apply(new Update().push("reviews").value(feedback)).first();
		return "Feedback Added";
	}
	
}
