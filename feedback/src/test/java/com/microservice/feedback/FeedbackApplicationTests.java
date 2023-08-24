package com.microservice.feedback;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Feedback;
import com.service.FeedbackService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class FeedbackApplicationTests {
	
	@Autowired
	FeedbackService fs;
	
	public static Feedback f1;
	public static Feedback f2;

	@BeforeAll
	static void setup() {
		f1 = new Feedback(1L, "david", "domino", "123", "good", 8.0);
		f2 = new Feedback(2L, "james", "kfc", "456", "bad", 4.0);
		
	}
	
	@Test
	@Order(1)
	void testEmptyFeedback() {
		assertEquals(fs.getAllFeedback().size(), 0);
	}
	
	@Test
	@Order(2)
	void testAddFeedback() {
		fs.createFeedback(f1);
		assertEquals(fs.getAllFeedback().size(), 1);
		fs.createFeedback(f2);
		assertEquals(fs.getAllFeedback().size(), 2);
	}

}
