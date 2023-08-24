package com.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document
public class Vendor {
	private String name;
	private String userName;
	private Long phoneNumber;
	private String address;
	private String password;
	private String email;
	private int rating;
	private boolean enabled = true;
	
	@DocumentReference
	private List<FeedBack> reviews;
}