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
public class Customer {
	@Id
	@GeneratedValue
	private long id;
	
	//required for registration
	private String email;
	private String password;
	
	//required to fill after registration
	private String name;
	private String userName;
	private Long phoneNumber =0l;
	private String address;
}
