package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Feedback {

	@Id
	@GeneratedValue
	private Long feedbackId;
	private String customerUserName;
	private String vendorUserName;
	private String foodId;
	private String body;
	private double rating;
	
}
