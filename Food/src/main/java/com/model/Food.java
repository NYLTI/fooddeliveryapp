package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="food")
public class Food {

	@Id
	private Long foodId;
	private String foodImage;
	private String foodName;
	private String vendorName;
	private Long price;
	private long discount=0;

}
