package com.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "reviews")
public class FeedBack {
	@Id
	private ObjectId id;
	
	private String vendorName;
	
	private String reviewer;

	private String body;
	private int rating;

	
	public FeedBack(String body, String reviewer) {
		this.body = body;
		this.reviewer = reviewer;
	}
}
